package table_types;
/**
 * 
 */

/**
 * @author azoni
 *
 */
public class Staff_Space {
	
	private int staffId;
	private int parkingSlotNo;
	private String lotName;
	
	/**
	 * @param staffId
	 * @param parkingSlotNo
	 */
	public Staff_Space(int staffId, int parkingSlotNo, String lotName) {
		super();
		this.staffId = staffId;
		this.parkingSlotNo = parkingSlotNo;
		this.lotName = lotName;
	}
	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}
	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	/**
	 * @return the parkingSlotNo
	 */
	public int getParkingSlotNo() {
		return parkingSlotNo;
	}
	/**
	 * @param parkingSlotNo the parkingSlotNo to set
	 */
	public void setParkingSlotNo(int parkingSlotNo) {
		this.parkingSlotNo = parkingSlotNo;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String name) {
		this.lotName = name;
	}
}
