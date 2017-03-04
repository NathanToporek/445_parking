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
	 * Constructor for a parking lot, not a physical one, though. Computers aren't that advanced.
	 * @param lotName The name of this lot.
	 * @param location The lovation of this lot
	 * @param capacity The capacity of this lot.
	 * @param floors The number of floors of this lot
	 * @param monthlyRate The monthly rate for this lot.
	 * @throws NullPointerException If lotName == null || location == null
	 * @throws IllegalArgumentException If capacity <= 0 || floors < 0 || monthlyRate < 0
	 */
	public Parking_Lot(String lotName, String location, int capacity, int floors, float monthlyRate)
		throws NullPointerException, IllegalArgumentException
	{
		if(lotName == null || location == null) {
			throw new NullPointerException("STOP GIVING ME EMPTY STRINGS YOU DUMB.");
		}
		if(capacity < 0 || floors < 0 || monthlyRate < 0) {
			throw new IllegalArgumentException("Negative Values aren't cool, yo.");
		}
		if(capacity == 0) {
			throw new IllegalArgumentException("YOU CAN'T HAVE AN EMPTY LOT.");
		}
		this.lotName = lotName;
		this.location = location;
		this.capacity = capacity;
		this.floors = floors;
		this.monthlyRate = monthlyRate;
	}
	/**
	 * @return this.lotName
	 */
	public String getLotName() {
		return lotName;
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
	 * Get the capacity for the lot.
	 * 
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * @return
	 */
	public float getMonthlyRate() {
		return monthlyRate;
	}

	@Override
	public String toString()
	{
		return "Parking Loit [name=" + lotName + ", location=" + location + ", capacity="
				+ capacity + ", floors=" + floors + ", monthly rate=" + monthlyRate
				+ "]";
	}	
	
}
