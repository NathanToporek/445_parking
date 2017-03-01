package table_types;

import java.sql.Date;

/**
 * @author azoni
 *
 */
public class Space_Booking {
	
	private int bookingId;
	private String visitorLisence;
	private Date dateOfVisit;
	private int staffId;
	private int parkingSlotNo;
	private String lotName;
	/**
	 * @param bookingId
	 * @param visitorLisence
	 * @param dateOfVisit
	 * @param staffId
	 * @param parkingLotNo
	 */
	public Space_Booking(int bookingId, String visitorLisence, Date dateOfVisit, int staffId, int parkingSlotNo, String lotName) {
		super();
		this.bookingId = bookingId;
		this.visitorLisence = visitorLisence;
		this.dateOfVisit = dateOfVisit;
		this.staffId = staffId;
		this.parkingSlotNo = parkingSlotNo;
		this.lotName = lotName;
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
	public Date getDateOfVisit() {
		return dateOfVisit;
	}
	/**
	 * @param dateOfVisit the dateOfVisit to set
	 */
	public void setDateOfVisit(Date dateOfVisit) {
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
	public int getParkingSlotNo() {
		return parkingSlotNo;
	}
	/**
	 * @param parkingLotNo the parkingLotNo to set
	 */
	public void setParkingSlotNo(int parkingLotNo) {
		this.parkingSlotNo = parkingLotNo;
	}
	public void setLotName(String lotname) {
		this.lotName = lotname;
	}
	public String getLotName() {
		return lotName;
	}
}
