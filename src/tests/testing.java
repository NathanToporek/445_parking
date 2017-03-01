package tests;

import java.util.ArrayList;

import backend.Parking_Lot_DB;
import backend.Parking_Slot_DB;
import backend.Staff_DB;
import table_types.Parking_Lot;
import table_types.Parking_Slot;
import table_types.Staff;

public class testing {
	public static void main(String ... args) {
		
		Parking_Lot lot = new Parking_Lot("TEST", "TEST", 50, 2, (float) 2.5);
		Parking_Slot slot = new Parking_Slot(1, "TEST", true, (float) 2.5);
		Staff staff = new Staff(0, 1234, "THE FIRST", "1234ABCD");
		
		ArrayList<Parking_Lot> l1 = null;
		ArrayList<Parking_Slot> s1 = null;
		ArrayList<Staff> st1 = null;
		
		try {
			Parking_Lot_DB.add_lot(lot);
			Parking_Slot_DB.add_slot(slot);
			Staff_DB.add_staff(staff);
			
			l1 = Parking_Lot_DB.get_parking_lots();
			s1 = Parking_Slot_DB.get_parking_slots();
			st1 = Staff_DB.get_staff();
			
			for(Parking_Lot l : l1) {
				System.out.println(l.getLotName());
			}
			for(Parking_Slot s : s1) {
				System.out.println(s.getParkingLotName());
			}
			for(Staff s : st1) {
				System.out.println(s.getName());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
