package table_types;

import java.sql.Date;

/**
 * Class to handle a single row from the SPACE_BOOKING relation in our database.
 * @author Charlton Smith, Nathanael Toporek
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Space_Booking {
	
	private int bookingId;
	private String visitorLisence;
	private Date dateOfVisit;
	private int staffId;
	private int parkingSlotNo;
	private String lotName;

	/**
	 * Constructor for this Space_Booking object.
	 * @param bookingId The booking ID for this space booking.
	 * @param visitorLisence License plate of the visitor.
	 * @param dateOfVisit Date of the visit.
	 * @param staffId ID of the staff member who reserved this slot.
	 * @param parkingSlotNo The Slot number of the slot that got reserved.
	 * @param lotName The Name of the parking lot that this slot was reserved in.
	 * @throws NullPointerException If visitorLicense == null
	 * 								|| dateOfVisit == null || lotName == null
	 * @throws IllegalArgumentException If any int is < 0
	 */
	public Space_Booking(int bookingId, String visitorLisence, Date dateOfVisit,
						 int staffId, int parkingSlotNo, String lotName)
		throws NullPointerException, IllegalArgumentException {

		if(bookingId < 0 || parkingSlotNo < 0 || staffId < 0) {
			throw new IllegalArgumentException("I don't like negatives.");
		}
		if(visitorLisence == null || lotName == null || dateOfVisit == null) {
			throw new NullPointerException("I don't like NULLs either.");
		}
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
	 * @return the visitorLisence
	 */
	public String getVisitorLisence() {
		return visitorLisence;
	}
	/**
	 * @return the dateOfVisit
	 */
	public Date getDateOfVisit() {
		return dateOfVisit;
	}
	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}
	/**
	 * @return the parkingLotNo
	 */
	public int getParkingSlotNo() {
		return parkingSlotNo;
	}
	/**
	 * @return The parking lot name.
	 */
	public String getLotName() {
		return lotName;
	}
	public String toString() {
		return "Booking ID: " + bookingId + " License: " + visitorLisence + " Date: " + dateOfVisit + " Staff ID: " + staffId + " Slot No: "
				+ parkingSlotNo + " Lot Name: " + lotName;
	}
	/**
	 * Normal .equals override.
	 * @param other Another object.
	 * @return True if all fields match, false otherwise.
	 */
	@Override
	public boolean equals (Object other) {
		if(other.getClass() == this.getClass()) {
			Space_Booking s = (Space_Booking) other;
			if(this.bookingId == s.bookingId &&
			   this.dateOfVisit.equals(s.dateOfVisit) &&
			   this.staffId == s.staffId &&
			   this.visitorLisence.equals(s.getVisitorLisence()) &&
			   this.parkingSlotNo == s.parkingSlotNo &&
			   this.lotName.equals(s.lotName))
			{
				return true;
			}
		}
		return false;
	}
}
