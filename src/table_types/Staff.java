package table_types;


/**
 * Class to handle a single row from the STAFF relation in our database.
 * @author Charlton Smith, Nathanael Toporek
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Staff {
	
	private int staffId;
	private int phoneExt;
	private String name;
	private String lisencePlateNo;

	/**
	 * Constructor for a Staff Member.
	 * @param staffId The ID for this staff.
	 * @param phoneExt The phone extension for this staff.
	 * @param name The name of this staff member.
	 * @param lisencePlateNo The license plate number for this staff member.
	 * @throws NullPointerException If name == null || licensePlateNo == null
	 * @throws IllegalArgumentException If staffId < 0 || phoneExt < 0 || phoneExt > 9999
	 */
	public Staff(int staffId, int phoneExt, String name, String lisencePlateNo)
		throws NullPointerException, IllegalArgumentException
	{
		if(staffId < 0 || phoneExt < 0) {
			throw new IllegalArgumentException("Negatives are bad.");
		}
		if(phoneExt > 9999) {
			throw new IllegalArgumentException("Invalid phone extension");
		}
		if(name == null || lisencePlateNo == null) {
			throw new NullPointerException("I don't like null values.");
		}
		this.staffId = staffId;
		this.phoneExt = phoneExt;
		this.name = name;
		this.lisencePlateNo = lisencePlateNo;
	}
	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}
	/**
	 * @return the phoneExt
	 */
	public int getPhoneExt() {
		return phoneExt;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the lisencePlateNo
	 */
	public String getLisencePlateNo() {
		return lisencePlateNo;
	}

	
}
