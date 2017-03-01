
/**
 * @author azoni
 *
 */
public class Staff {
	
	private int staffId;
	private int phoneExt;
	private String name;
	private String lisencePlateNo;
	/**
	 * @param staffId
	 * @param phoneExt
	 * @param name
	 * @param lisencePlateNo
	 */
	public Staff(int staffId, int phoneExt, String name, String lisencePlateNo) {
		super();
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
	 * @param staffId the staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	/**
	 * @return the phoneExt
	 */
	public int getPhoneExt() {
		return phoneExt;
	}
	/**
	 * @param phoneExt the phoneExt to set
	 */
	public void setPhoneExt(int phoneExt) {
		this.phoneExt = phoneExt;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lisencePlateNo
	 */
	public String getLisencePlateNo() {
		return lisencePlateNo;
	}
	/**
	 * @param lisencePlateNo the lisencePlateNo to set
	 */
	public void setLisencePlateNo(String lisencePlateNo) {
		this.lisencePlateNo = lisencePlateNo;
	}
	
	
}
