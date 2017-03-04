package complexqueries;

import backend.*;
import table_types.*;

import javax.naming.LimitExceededException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static backend.BasicDB.create_connection;
import static backend.BasicDB.username;

/**
 * Class to handle queries that need to span multiple relations.
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Complex_Queries {

    public static final int MAX_VISITORS = 20;
    /**
     * Gets a list of available slots given a lot name.
     * @param lotName The lot we're searching in.
     * @return a list of available slots given a lot name.
     * @throws NullPointerException if lotName == null
     */
    public static ArrayList<Parking_Slot> available_slots_by_lot_name(String lotName)
        throws NullPointerException, SQLException
    {
        if(BasicDB.conn == null) {
            BasicDB.create_connection();
        }
        ArrayList<Parking_Slot> not_available = new ArrayList<Parking_Slot>();
        for(Staff_Space ss : Staff_Space_DB.get_staff_spaces()) {
            if(Parking_Slot_DB.by_primary_key(ss.getLotName(), ss.getParkingSlotNo()).size() > 0) {
                not_available.add(Parking_Slot_DB.by_primary_key(ss.getLotName(), ss.getParkingSlotNo()).get(0));
            }
        }
        for(Space_Booking sb : Space_Booking_DB.get_bookings()) {
            if (sb.getDateOfVisit().equals(new Date(Instant.now().toEpochMilli()))
                    && Parking_Slot_DB.by_primary_key(sb.getLotName(), sb.getParkingSlotNo()).size() > 0) {
                not_available.add(Parking_Slot_DB.by_primary_key(sb.getLotName(), sb.getParkingSlotNo()).get(0));
            }
        }
        HashSet<Parking_Slot> available = new HashSet<Parking_Slot>();
        for(Parking_Slot p : Parking_Slot_DB.by_lot_name(lotName)) {
            if(not_available.size() > 0) {
                for (Parking_Slot na : not_available) {
                    if (!not_available.contains(p)) {
                        available.add(p);
                    }
                }
            } else {
                available.add(p);
            }
        }
        ArrayList<Parking_Slot> res = new ArrayList<Parking_Slot>(available);
        Collections.sort(res);
        return res;
    }

    /**
     * Generates a parking lot and all of its parking slots.
     * @param covered The number of covered parking slots in this lot.
     * @param uncovered The number of uncovered parking slots in this lot.
     * @param floors The number of floors in this parking lot.
     * @param lotName The name of this parking lot
     * @param location The location of this parking lot.
     * @param monthlyRate The Monthly rate of this parking lot.
     * @throws IllegalArgumentException If covered < 0 || uncovered < 0
     */
    public static void generate_lot(int covered, int uncovered, int floors, String lotName, String location, float monthlyRate)
        throws IllegalArgumentException {
        if (covered < 0 || uncovered < 0) {
            throw new IllegalArgumentException("pls no negativ nmb3rz");
        }
        Parking_Lot lot = null;
        int capacity = uncovered + covered;
        try {
            lot = new Parking_Lot(lotName, location, capacity, floors, monthlyRate);
            Parking_Lot_DB.add_lot(lot);
            // Make covered many covered slots.
            for (int i = 0; i < covered; i++) {
                Parking_Slot slot = new Parking_Slot(i, lotName, true);
                Parking_Slot_DB.add_slot(slot);
            }
            // Make uncovered many uncovered slots.
            for (int i = 0; i < uncovered; i++) {
                Parking_Slot slot = new Parking_Slot(i + covered, lotName, false);
                Parking_Slot_DB.add_slot(slot);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Reserves a parking spot for a staff member.
     * @param space The Staff_Space we're trying to reserve.
     * @throws NullPointerException If Space == null
     * @throws IllegalArgumentException If things are wrong with the DB.
     */
    public static void reserve_for_staff(Staff_Space space)
        throws NullPointerException, IllegalArgumentException
    {
        if(space == null) {
            throw new NullPointerException("I don't like null values");
        }
        // If the lot doesn't exist.
        if(Parking_Lot_DB.get_parking_lots(space.getLotName()).isEmpty()) {
            throw new IllegalArgumentException("Lot does not exist.");
        }
        // If the slot doesn't exist.
        if(Parking_Slot_DB.by_primary_key(space.getLotName(), space.getParkingSlotNo()).isEmpty()) {
            throw new IllegalArgumentException("Slot does not exist as you want it to.");
        }
        //If the staff member isn't in our DB
        if(Staff_DB.staff_by_ID(space.getStaffId()).isEmpty()) {
            throw new IllegalArgumentException("Staff Member does not exist.");
        }
        // If the spot is reserved by staff.
        if(!Staff_Space_DB.by_primary_key(space.getParkingSlotNo(), space.getStaffId(), space.getLotName()).isEmpty()) {
            throw new IllegalArgumentException("Space is reserved by staff already.");
        }
        // If the slot is reserved for a visitor today.
        ArrayList<Space_Booking> reserved = new ArrayList<Space_Booking>();
        try {
            reserved = Space_Booking_DB.by_foreign_keys_and_todays_date(space.getLotName(), space.getParkingSlotNo(), space.getStaffId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!reserved.isEmpty()) {
            throw new IllegalArgumentException("Space is reserved for a visitor today.");
        }
        // If the slot is covered.
        if(!Parking_Slot_DB.by_primary_key(space.getLotName(), space.getParkingSlotNo()).get(0).isCovered()) {
            throw new IllegalArgumentException("Space is uncovered u idiot.");
        }
        // Now that we've successfully confirmed that the user is indeed NOT retarded, we can make a
        // staff reservation.
        try {
            Staff_Space_DB.add_space(space);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Reserves a space for a guest.
     * @param booking The Space_Booking object we want to put into our database.
     * @throws LimitExceededException If we've already reserved 20 spots for visitors.
     * @throws NullPointerException if booking == null
     * @throws IllegalArgumentException If things are wrong with the DB.
     */
    public static void reserve_for_guest(Space_Booking booking)
        throws LimitExceededException, NullPointerException, IllegalArgumentException, SQLException
    {
        // If we've already had 20 vistor reservations.
        ArrayList<Space_Booking> bookings = new ArrayList<Space_Booking>();
        try {
           bookings = Space_Booking_DB.get_bookings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bookings.size() >= MAX_VISITORS) {
            throw new LimitExceededException("We've already made 20 guest reservations.");
        }
        // Null check
        if(booking == null) {
            throw new NullPointerException("NO NULLS");
        }
        // If the date is before today.
        if(booking.getDateOfVisit().before(new Date(Instant.now().toEpochMilli()))) {
            throw new IllegalArgumentException("YOU CAN'T RESERVE SPOTS IN THE PAST.");
        }
        // If the lot doesn't exist
        if(Parking_Lot_DB.get_parking_lots(booking.getLotName()).isEmpty()) {
            throw new IllegalArgumentException("Lot doesn't exist.");
        }
        // If the slot doesn't exist in our lot.
        if(Parking_Slot_DB.by_primary_key(booking.getLotName(), booking.getParkingSlotNo()).isEmpty()) {
            throw new IllegalArgumentException("Slot doesn't exist in your lot.");
        }
        // If the staff member doesn't exist
        if(Staff_DB.staff_by_ID(booking.getStaffId()).isEmpty()) {
            throw new IllegalArgumentException("Staff doesn't exist.");
        }
        // If this slot is reserved by a staff member.
        if(!Staff_Space_DB.by_primary_key(booking.getParkingSlotNo(), booking.getStaffId(), booking.getLotName()).isEmpty()) {
            throw new IllegalArgumentException("Space is reserved by staff already.");
        }
        // If this slot is reserved for a visitor today.
        ArrayList<Space_Booking> reserved = new ArrayList<Space_Booking>();
        try {
            reserved = Space_Booking_DB.by_foreign_keys_and_todays_date(booking.getLotName(), booking.getParkingSlotNo(), booking.getStaffId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!reserved.isEmpty()) {
            throw new IllegalArgumentException("Space is reserved for a visitor today.");
        }
        // If the slot is covered.
        if(!Parking_Slot_DB.by_primary_key(booking.getLotName(), booking.getParkingSlotNo()).get(0).isCovered()) {
            throw new IllegalArgumentException("Space is uncovered u idiot.");
        }
        // Now that we know that everything is good and right in our world, we make a reservation.
        Space_Booking_DB.add_space_booking(booking);
    }
}
