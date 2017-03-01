package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import table_types.Parking_Lot;

/**
 * Class to handle data stored in the PARKING_LOT relation.
 * 
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Parking_Lot_DB extends BasicDB{
	/**
	 * Opens a connection if need be, then gets all of the Parking lots in our DB.
	 * @return A list of all the parking lots our company owns.
	 * @throws SQLException If ur dum
	 */
	public static ArrayList<Parking_Lot> get_parking_lots() throws SQLException {
		if(conn == null) {
			create_connection();
		}
		Statement statement = null;
		String quer = "SELECT Lot_Name, Location, Capacity, Floors, Monthly_Rate" 
					+ " FROM nat96.PARKING_LOT";
		ArrayList<Parking_Lot> lot_list = new ArrayList<Parking_Lot>();
		try {
			statement = conn.createStatement();
			ResultSet r = statement.executeQuery(quer);
			
			while(r.next()) {
				
				lot_list.add(new Parking_Lot(r.getString("Lot_Name"),
											 r.getString("Location"),
											 r.getInt("Capacity"),
											 r.getInt("Floors"),
											 r.getFloat("Monthly_Rate")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(statement != null) {
				statement.close();
			}
		}
		return lot_list;
	}
	/**
	 * Gets all of the Parking Lots filtered by the name you give us.
	 * @param lot_name The name of the parking lot you want to search for.
	 * @return A list of all the parking lots matching the name you passed.
	 */
	public static List<Parking_Lot> get_parking_lots(String lot_name) {
		ArrayList<Parking_Lot> lots = new ArrayList<Parking_Lot>();
		ArrayList<Parking_Lot> list = new ArrayList<Parking_Lot>();
		try{
			lots = get_parking_lots();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Parking_Lot lot : lots) {
			if(lot.getLotName().compareToIgnoreCase(lot_name) == 0) {
				list.add(lot);
			}
		}
		return list;
	}
	/**
	 * Adds a parking lot to this Database. 
	 * @param lot The lot you want to add to this DB.
	 */
	public static void add_lot(Parking_Lot lot) throws SQLException {
		// DON'T BE A DUMB
		if(lot == null) {
			return;
		}
		if(conn == null) {
			create_connection();
		}
		String quer = "INSERT INTO " + username + ".PARKING_LOT" 
					+ "(Lot_Name, Location, Capacity, Floors, Monthly_Rate) "  
					+ "VALUES (?, ?, ?, ?, ?);";
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(quer);
			ps.setString(1, lot.getLotName());
			ps.setString(2, lot.getLocation());
			ps.setInt(3, lot.getCapacity());
			ps.setInt(4, lot.getFloors());
			ps.setFloat(5, lot.getMonthlyRate());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
