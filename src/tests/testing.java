package tests;

import java.util.ArrayList;

import backend.Parking_Lot_DB;
import backend.Parking_Slot_DB;
import backend.Staff_DB;
import complexqueries.Complex_Queries;
import table_types.Parking_Lot;
import table_types.Parking_Slot;
import table_types.Staff;

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
			
			for(Parking_Lot l : l1) {
				System.out.println(l.getLotName());
			}
			for(Parking_Slot s : s1) {
				System.out.println(s.getParkingLotName() + " " + s.getSlotNo() + " " + s.isCovered());
			}
			for(Staff s : st1) {
				System.out.println(s.getName());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
