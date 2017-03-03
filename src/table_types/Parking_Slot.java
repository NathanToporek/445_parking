package table_types;

/**
 * @author azoni and nat96
 * @version 3/1/2017
 * Class to handle information about a parking slot in our PARKING_SLOT relation.
 * @author Charlton Smith, Nathanael Toporek
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Parking_Slot {
	/**
	 * This slots Slot Number
	 */
	private int slotNo;
	/**
	 * The name of the parking lot that contains this parking slot.
	 */
	private String parkingLotName;
	/**
	 * Determines whether or not you pay cashmaneyz for this slot.
	 */
	private boolean isCovered;

    /**
     * Creates a new Parking_Slot object.
     * @param slotNo The slot number.
     * @param parkingLotName The name of the parking lot that contains this slot.
     * @param isCovered Is the slot covered or not?
     * @throws IllegalArgumentException If slotNo < 0
     * @throws NullPointerException if parkingLotName == null
     */
	public Parking_Slot(int slotNo, String parkingLotName, boolean isCovered)
		throws IllegalArgumentException, NullPointerException
	{
		if(slotNo < 0) {
			throw new IllegalArgumentException("Slots can't have a negative ID #");
		}
		if(parkingLotName == null) {
			throw new NullPointerException("Please enter an actual lot name, not just blank space. *Scoffs*");
		}
		this.slotNo = slotNo;
		this.parkingLotName = parkingLotName;
		this.isCovered = isCovered;
	}
	/**
	 * @return the slotNo
	 */
	public int getSlotNo() {
		return slotNo;
	}
	/**
	 * @return the parkingLotName
	 */
	public String getParkingLotName() {
		return parkingLotName;
	}
	/**
	 * @return the isCovered
	 */
	public boolean isCovered() {
		return isCovered;
	}

}
