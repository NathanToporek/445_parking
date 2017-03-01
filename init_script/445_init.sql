CREATE TABLE PARKING_LOT (
	Lot_Name	VARCHAR(30)	NOT NULL,
	Location	VARCHAR(30)	NOT NULL,
	Capacity	INT		NOT NULL,
	Floors		INT		NOT NULL,
	Monthly_Rate	FLOAT(10)	NOT NULL,
	PRIMARY KEY(Lot_Name)
);

CREATE TABLE PARKING_SLOT (
	Slot_No		INT		NOT NULL,
	Lot_Name	VARCHAR(30)	NOT NULL,
	Is_Covered	BOOLEAN		NOT NULL,
	PRIMARY KEY(Slot_No, Lot_Name)
);

CREATE TABLE STAFF (
	Staff_ID		INT		NOT NULL,
	Staff_Name		VARCHAR(50)	NOT NULL,
	Phone_Extension		INT		NOT NULL,
	License_Plate_No	VARCHAR(12)	NOT NULL,
	PRIMARY KEY(Staff_ID)
);

CREATE TABLE STAFF_SPACE (
	Slot_No		INT		NOT NULL,
	Lot_Name	VARCHAR(30)	NOT NULL,
	Staff_ID	INT		NOT NULL,
	PRIMARY KEY(Slot_No, Lot_Name, Staff_ID)
);

CREATE TABLE SPACE_BOOKING (
	Booking_ID	INT		NOT NULL,
	Visitor_License	VARCHAR(12)		NOT NULL,
	Date_of_Visit	DATE		NOT NULL,
	Staff_ID	INT		NOT NULL,
	Slot_No		INT		NOT NULL,
	Lot_Name	VARCHAR(30)	NOT NULL,
	PRIMARY KEY(Booking_ID)
);
