package table_types;
/**
 * Represents a parking lot with a name, location, capacity, floors, and monthly rate.
 * @author Charlton, Nathan
 *
 */
public class Parking_Lot {
	private String lotName;
	private String location;
	private int capacity;
	private int floors;
	private float monthlyRate;
	
	/**
	 * Constructor for Parking lot Structure 
	 * 
	 * @param lotName
	 * @param location
	 * @param capacity
	 * @param floors
	 * @param monthlyRate
	 */
	public Parking_Lot(String lotName, String location, int capacity, int floors, float monthlyRate) {
		this.lotName = lotName;
		this.location = location;
		this.capacity = capacity;
		this.floors = floors;
		this.monthlyRate = monthlyRate;
	}
	/**
	 * @return
	 */
	public String getLotName() {
		return lotName;
	}

	/**
	 * @param lotName
	 */
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	/**
	 * Get the parking lot location.
	 * 
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Get the capacity for the lot.
	 * 
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * @param floors
	 */
	public void setFloors(int floors) {
		this.floors = floors;
	}

	/**
	 * @return
	 */
	public float getMonthlyRate() {
		return monthlyRate;
	}

	/**
	 * @param monthlyRate
	 */
	public void setMonthlyRate(float monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	
	@Override
	public String toString()
	{
		return "Parking Loit [name=" + lotName + ", location=" + location + ", capacity="
				+ capacity + ", floors=" + floors + ", monthly rate=" + monthlyRate
				+ "]";
	}	
	
}
