package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import table_types.Space_Booking;
/**
 * Class to handle connections to the SPACE_BOOKING relation in our database.
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Space_Booking_DB extends BasicDB {
	/**
	 * Returns a list of all space_bookings in our DB.
	 * @return Returns a list of all space_bookings in our DB.
	 */
	public static ArrayList<Space_Booking> get_bookings() throws SQLException {
		if(conn == null) {
			create_connection();
		}
		ArrayList<Space_Booking> spaces = new ArrayList<Space_Booking>();
		
		Statement s = null;
		String quer = "SELECT Booking_ID, Visitor_License, Date_of_Visit, Staff_ID" 
					+ ", Slot_No, Lot_Name "
					+ "FROM " + username + ".SPACE_BOOKING";
		
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery(quer);
			while(r.next()) {
				spaces.add(new Space_Booking(r.getInt("Booking_ID"),
											 r.getString("Visitor_License"),
											 r.getDate("Date_of_Visit"),
											 r.getInt("Staff_ID"),
											 r.getInt("Slot_No"),
											 r.getString("Lot_Name")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return spaces;
	}
	/**
	 * Gets all of the space_bookings associated with a single staff member.
	 * @param id The ID of the staff memeber we want to check on.
	 * @return all of the space_bookings associated with a single staff member.
	 */
	public static ArrayList<Space_Booking> by_staff_id(int id) throws SQLException {
		ArrayList<Space_Booking> all = get_bookings();
		ArrayList<Space_Booking> filtered = new ArrayList<Space_Booking>();
		
		for(Space_Booking booking : all) {
			if(booking.getStaffId() == id) {
				filtered.add(booking);
			}
		}
		return filtered;
	}
	/**
	 * Adds a space_booking object to this database.
	 * @param booking The booking to be added to this database.
	 */
	public static void add_space_booking(Space_Booking booking) throws SQLException {
		// DON'T BE A DUMB
		if(booking == null) {
			return;
		}
		if(conn == null) {
			create_connection();
		}
		String quer = "INSERT INTO " + username + ".SPACE_BOOKING "
					+ "VALUES (?, ?, ?, ?, ?, ?, NULL);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setInt(1, booking.getBookingId());
			ps.setString(2, booking.getVisitorLisence());
			ps.setDate(3, booking.getDateOfVisit());
			ps.setInt(4, booking.getStaffId());
			ps.setInt(5, booking.getParkingSlotNo());
			ps.setString(6, booking.getLotName());
			ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
