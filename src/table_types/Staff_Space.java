package table_types;

import java.nio.file.NotLinkException;

/**
 * A class to hangle a single row of information from the STAFF_SPACE relation in our database.
 * @author Charlton Smith, Nathanael Toporek
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Staff_Space {
	
	private int staffId;
	private int parkingSlotNo;
	private String lotName;

	/**
	 * Constructor for a staff_space object.
	 * @param staffId The ID of the staff member making this reservation.
	 * @param parkingSlotNo The slot number that is being reserved
	 * @param lotName The lot we're making a reservation in.
	 * @throws NullPointerException If lotName == null
	 * @throws IllegalArgumentException If staffId < 0 || parkingSlotNo < 0
	 */
	public Staff_Space(int staffId, int parkingSlotNo, String lotName)
		throws NullPointerException, IllegalArgumentException
	{
		if(staffId < 0 || parkingSlotNo < 0) {
			throw new IllegalArgumentException("Stop giving me negative u dum");
		}
		if(lotName == null) {
			throw new NullPointerException("I don't like nulls.");
		}
		this.staffId = staffId;
		this.parkingSlotNo = parkingSlotNo;
		this.lotName = lotName;
	}
	/**
	 * Returns the ID of the staff member that reserved this parking slot.
	 * @return this.staffId
	 */
	public int getStaffId() {
		return staffId;
	}
	/**
	 * Returns the parking slot ID number for this reservation.
	 * @return this.parkingSlotNo
	 */
	public int getParkingSlotNo() {
		return parkingSlotNo;
	}

	/**
	 * Returns the lot name for this reservation.
	 * @return this.lotName.
	 */
	public String getLotName() {
		return lotName;
	}
}
