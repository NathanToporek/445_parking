package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import table_types.Staff;
import table_types.Staff_Space;
/**
 * Class to handle connections to the STAFF_SPACE relation in this database.
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Staff_Space_DB extends BasicDB {
	/**
	 *  Gets all of the staff spaces.
	 * @return Returns a list of all the staff-reserved parking spaces in all of our lots.
	 * @throws SQLException if u dum
	 */
	public static ArrayList<Staff_Space> get_staff_spaces() throws SQLException {
		if(conn == null) {
			create_connection();
		}
		Statement s = null;
		String quer = "SELECT Slot_No, Lot_Name, Staff_ID FROM " 
					+ username + ".STAFF_SPACE;";
		
		ArrayList<Staff_Space> spaces = new ArrayList<Staff_Space>();
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery(quer);
			while(r.next()) {
				spaces.add(new Staff_Space(r.getInt("Slot_No"),
										   r.getInt("Staff_ID"),
										   r.getString("Lot_Name")));
			}
		} catch(SQLException e) {
			System.out.println(e);
		} finally {
			if(s != null) {
				s.close();
			}
		}
		return spaces;
	}
	/**
	 * Gets a list of all the staff_spaces in a given lot name.
	 * @param lot_name The lot that we're looking for.
	 * @return A list of all of the staff-spaces in the specified lot.
	 */
	public static ArrayList<Staff_Space> by_lot_name(String lot_name) {
		ArrayList<Staff_Space> all = null;
		try {
			all = get_staff_spaces();
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		ArrayList<Staff_Space> filtered = new ArrayList<Staff_Space>();
		
		for(Staff_Space space : all) {
			if(space.getLotName().compareToIgnoreCase(lot_name) == 0) {
				filtered.add(space);
			}
		}
		return filtered;
	}
	/**
	 * Returns all staff-spaces reserved for a single staff member.
	 * @param id The ID of the staff member you want to look up
	 * @return All of the staff spaces reserved by the given staff member.
	 */
	public static ArrayList<Staff_Space> by_staff_ID(int id) {
		ArrayList<Staff_Space> all = null;
		try {
			all = get_staff_spaces();
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		ArrayList<Staff_Space> filtered = new ArrayList<Staff_Space>();
		
		for(Staff_Space space : all) {
			if(space.getStaffId() == id) {
				filtered.add(space);
			}
		}
		return filtered;
	}

	/**
	 * Queries the DB by primary key values
	 * @param slotNo The slot number for the staff booking
	 * @param staffID The Staff Id of the staff booking.
	 * @param lotName The lot name of the staff booking.
	 * @return A list of (hopefully) size 1 containing a matching Staff_Space object.
	 */
	public static ArrayList<Staff_Space> by_primary_key(int slotNo, int staffID, String lotName) {
		ArrayList<Staff_Space> all = null;
		try {
			all = get_staff_spaces();
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		ArrayList<Staff_Space> filtered = new ArrayList<Staff_Space>();

		for(Staff_Space space : all) {
			if(space.getStaffId() == staffID &&
			   space.getParkingSlotNo() == slotNo &&
			   space.getLotName().equals(lotName))
			{
				filtered.add(space);
			}
		}
		return filtered;
	}
	/**
	 * Adds a space to this database.
	 * @param space The space to be added to our relation.
	 */
	public static void add_space(Staff_Space space) throws SQLException {
		// DON'T BE DUM
		if(space == null) {
			return;
		}
		if(conn == null) {
			create_connection();
		}
		String quer = "INSERT INTO " + username + ".STAFF_SPACE VALUES " 
					+ "(?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setInt(1, space.getParkingSlotNo());
			ps.setInt(2,  space.getStaffId());
			ps.setString(3, space.getLotName());
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
