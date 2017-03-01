package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import table_types.Parking_Slot;

/**
 * Class for Handling connections to the PARKING_SLOT relation in this database.
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Parking_Slot_DB extends BasicDB {
	
	/**
	 * Returns a list of all the parking slots stored in this database.
	 * @return See above.
	 * @throws SQLException if ur dum
	 */
	public static ArrayList<Parking_Slot> get_parking_slots() throws SQLException{
		if(conn == null) {
			create_connection();
		}
		Statement s = null;
		String quer = "SELECT Slot_No, Lot_Name, Is_Covered " 
					+ "FROM " + username + ".PARKING_SLOT;";
		ResultSet r = null;
		ArrayList<Parking_Slot> slots = new ArrayList<Parking_Slot>();
		try{
			s = conn.createStatement();
			r = s.executeQuery(quer);
			
			while(r.next()) {
				slots.add(new Parking_Slot(r.getInt("Slot_No"),
										   r.getString("Lot_Name"),
										   r.getBoolean("Is_Covered"),
										   (float) 0.0));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(s != null) {
				s.close();
			}
		}
		
		return slots;
	}
	/**
	 * Returns all of the parking slots that have the same lot_name as you requested.
	 * @param lot_name The parking lot name we want to filter by.
	 * @return All slots in the lot you request.
	 */
	public static ArrayList<Parking_Slot> slots_by_lot_name(String lot_name) {
		ArrayList<Parking_Slot> unfiltered = null;
		try {
			unfiltered = get_parking_slots();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Parking_Slot> filtered = new ArrayList<Parking_Slot>();
		for(Parking_Slot slot : unfiltered) {
			if(slot.getParkingLotName().compareToIgnoreCase(lot_name) == 0) {
				filtered.add(slot);
			}
		}
		return filtered;
	}
	/**
	 * Adds a parking slot to this database.
	 * @param slot The slot you want to add.
	 */
	public static void add_slot(Parking_Slot slot) throws SQLException {
		// DON'T BE A DUMB
		if(slot == null) {
			return;
		}
		if(conn == null) {
			create_connection();
		}
		String quer = "INSERT INTO " + username + ".PARKING_SLOT " 
					+ "VALUES (?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setInt(1, slot.getSlotNo());
			ps.setString(2, slot.getParkingLotName());
			ps.setBoolean(3, slot.isCovered());
			ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
