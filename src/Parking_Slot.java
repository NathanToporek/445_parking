
/**
 * @author azoni
 *
 */
public class Parking_Slot {
	/**
	 * 
	 */
	private int slotNo;
	/**
	 * 
	 */
	private String parkingLotName;
	/**
	 * 
	 */
	private boolean isCovered;
	/**
	 * 
	 */
	private float monthlyRate;
	
	/**
	 * @param slotNo
	 * @param parkingLotName
	 * @param isCovered
	 * @param monthlyRate
	 */
	public Parking_Slot(int slotNo, String parkingLotName, boolean isCovered, float monthlyRate) {
		super();
		this.slotNo = slotNo;
		this.parkingLotName = parkingLotName;
		this.isCovered = isCovered;
		this.monthlyRate = monthlyRate;
	}
	/**
	 * @return the slotNo
	 */
	public int getSlotNo() {
		return slotNo;
	}
	/**
	 * @param slotNo the slotNo to set
	 */
	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}
	/**
	 * @return the parkingLotName
	 */
	public String getParkingLotName() {
		return parkingLotName;
	}
	/**
	 * @param parkingLotName the parkingLotName to set
	 */
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	/**
	 * @return the isCovered
	 */
	public boolean isCovered() {
		return isCovered;
	}
	/**
	 * @param isCovered the isCovered to set
	 */
	public void setCovered(boolean isCovered) {
		this.isCovered = isCovered;
	}
	/**
	 * @return the monthlyRate
	 */
	public float getMonthlyRate() {
		return monthlyRate;
	}
	/**
	 * @param monthlyRate the monthlyRate to set
	 */
	public void setMonthlyRate(float monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	
}
