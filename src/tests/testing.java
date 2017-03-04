package tests;

import java.util.ArrayList;

import backend.Parking_Lot_DB;
import backend.Parking_Slot_DB;
import backend.Staff_DB;
import backend.Staff_Space_DB;
import complexqueries.Complex_Queries;
import table_types.Parking_Lot;
import table_types.Parking_Slot;
import table_types.Staff;
import table_types.Staff_Space;

public class testing {
	public static void main(String ... args) {

		String lotName = "test";
		String loc = "test";
		int cov = 10;
		int ucov = 20;
		int flors = 1;
		float rate = (float) 2.5;

		//Staff staff = new Staff(0, 1234, "THE FIRST", "1234ABCD");
		
		ArrayList<Parking_Lot> l1 = null;
		ArrayList<Parking_Slot> s1 = null;
		ArrayList<Staff> st1 = null;
		
		try {
			//Complex_Queries.generate_lot(cov, ucov, flors, lotName, loc, rate);
			//Staff_DB.add_staff(staff);
			
			l1 = Parking_Lot_DB.get_parking_lots();
			s1 = Parking_Slot_DB.get_parking_slots();
			st1 = Staff_DB.get_staff();

			ArrayList<Staff_Space> ss = Staff_Space_DB.get_staff_spaces();
			System.out.println(String.format("%d %d %s", ss.get(0).getStaffId(), ss.get(0).getParkingSlotNo(), ss.get(0).getLotName()));

			ArrayList<Parking_Slot> na = Parking_Slot_DB.by_primary_key(ss.get(0).getLotName(), ss.get(0).getParkingSlotNo());
			System.out.println(String.format("%d %s", na.get(0).getSlotNo(), na.get(0).getParkingLotName()));

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
