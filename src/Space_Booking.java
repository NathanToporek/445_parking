
/**
 * @author azoni
 *
 */
public class Space_Booking {
	
	private int bookingId;
	private String visitorLisence;
	private String dateOfVisit;
	private int staffId;
	private int parkingLotNo;
	/**
	 * @param bookingId
	 * @param visitorLisence
	 * @param dateOfVisit
	 * @param staffId
	 * @param parkingLotNo
	 */
	public Space_Booking(int bookingId, String visitorLisence, String dateOfVisit, int staffId, int parkingLotNo) {
		super();
		this.bookingId = bookingId;
		this.visitorLisence = visitorLisence;
		this.dateOfVisit = dateOfVisit;
		this.staffId = staffId;
		this.parkingLotNo = parkingLotNo;
	}
	/**
	 * @return the bookingId
	 */
	public int getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @return the visitorLisence
	 */
	public String getVisitorLisence() {
		return visitorLisence;
	}
	/**
	 * @param visitorLisence the visitorLisence to set
	 */
	public void setVisitorLisence(String visitorLisence) {
		this.visitorLisence = visitorLisence;
	}
	/**
	 * @return the dateOfVisit
	 */
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	/**
	 * @param dateOfVisit the dateOfVisit to set
	 */
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
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
	 * @return the parkingLotNo
	 */
	public int getParkingLotNo() {
		return parkingLotNo;
	}
	/**
	 * @param parkingLotNo the parkingLotNo to set
	 */
	public void setParkingLotNo(int parkingLotNo) {
		this.parkingLotNo = parkingLotNo;
	}
}
