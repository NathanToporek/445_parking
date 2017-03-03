package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import table_types.Staff;
/**
 * Class to handle connections to the STAFF relation
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Staff_DB extends BasicDB {
	/**
	 * Gets all of the staff members that we keep track of.
	 * @return A list of all the staff we track.
	 * @throws SQLException if ur dum
	 */
	public static ArrayList<Staff> get_staff() throws SQLException {
		if(conn == null) {
			create_connection();
		}
		Statement s = null;
		String quer = "SELECT Staff_ID, Staff_Name, Phone_Extension, License_Plate_No " 
					+ "FROM " + username + ".STAFF;";
		ArrayList<Staff> staff = new ArrayList<Staff>();
		try{
			s = conn.createStatement();
			ResultSet r = s.executeQuery(quer);
			while(r.next()) {
				staff.add(new Staff(r.getInt("Staff_ID"),
									r.getString("Staff_Name"),
									r.getInt("Phone_Extension"),
									r.getString("License_Plate_No")));
			}
		} catch(SQLException e) {
			System.out.println(e);
		} finally {
			if(s != null) {
				s.close();
			}
		}
		return staff;
	}
	/**
	 * Returns hopefully a single staff member based off of the ID you pass.
	 * @param id The ID of the staff member you're stalking.
	 * @return The Staff member you're stalking. GET DAT EXTENSION BOIIIIIIII
	 */
	public static ArrayList<Staff> staff_by_ID(int id) {
		ArrayList<Staff> all = null;
		ArrayList<Staff> filtered = new ArrayList<Staff>();
		try {
			all = get_staff();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		for(Staff member : all) {
			if(member.getStaffId() == id) {
				filtered.add(member);
			}
		}
		return filtered;
	}
	/**
	 * Adds a staff member to this database.
	 * @param staff The staff member you want to track. You stalker you. ;)
	 */
	public static void add_staff(Staff staff) throws SQLException {
		// DON'T BE A DUMB
		if(staff == null) {
			return;
		}
		if(conn == null) {
			create_connection();
		}
		String quer = "INSERT INTO " + username + ".STAFF "
					+ "VALUES (?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setInt(1, staff.getStaffId());
			ps.setString(2,  staff.getName());
			ps.setInt(3, staff.getPhoneExt());
			ps.setString(4, staff.getLisencePlateNo());
			ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	/**
	 * Updates the phone extension of the staff member you're tracking
	 * @param id The id of the staff member that you want to update.
	 * @param ext The new extension of the staff member.
	 */
	public static void update_staff_extension(int id, int ext) throws SQLException {
		if(conn == null) {
			create_connection();
		}

		String quer = "UPDATE " + username + ".STAFF SET Phone_Extension= ? "
					+ "WHERE Staff_ID = ?;";
		System.out.println(quer);
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setInt(1, ext);
			ps.setInt(2, id);
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	/**
	 * Updates the License plate # of the staff member you're tracking.
	 * @param id The id of the staff member that you want to update.
	 * @param lic Their new license plate number.
	 */
	public void update_staff_license(int id, String lic) throws SQLException {

		if(conn == null) {
			create_connection();
		}
		String quer = "UPDATE " + username + ".STAFF SET License_Plate_No= ? "
					+ "WHERE Staff_ID = ?;";
		System.out.println(quer);
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setString(1, lic);
			ps.setInt(2, id);
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
