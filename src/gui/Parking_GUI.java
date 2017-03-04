package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import backend.Parking_Lot_DB;
import backend.Space_Booking_DB;
import backend.Staff_DB;
import backend.Staff_Space_DB;
import complexqueries.Complex_Queries;
import table_types.Parking_Lot;
import table_types.Space_Booking;
import table_types.Staff;
import table_types.Staff_Space;

/**
 * Class that does a large bit of work for displaying parking lots and the information
 * related to them.
 * @author Nathanael Toporek, Chalton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Parking_GUI extends JFrame implements ActionListener, TableModelListener
{
	private static final long serialVersionUID = 1779520078061383929L;
	private JButton btnListLots, btnBookingList, btnListStaff, btnSearch, btnAddLot,
			btnAddStaff, btnUpdate, btnAddStaffConfirm, btnUpdateStaff;
	private JPanel pnlButtons, pnlContent;
	private List<Parking_Lot> lotList;
	private List<Space_Booking> reserveList;
	private List<Staff> staffList;
	private String[] lotColumns = {"Lot Name",
            "Location",
            "Capacity",
            "Floors",
            "Monthly Rate"};
	private String[] staffColumns = {"ID", "Name", "Phone Ext.", "License Plate #"};
	private String[] staff_space_col = {"Staff ID", "Lot Name", "Slot #"};
	private String[] space_book_col = {"Staff ID", "Lot Name", "Slot #", "License #", "Date of Visit"};
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch;
	private JButton btnReserve;
	private JPanel pnlAddLot, pnlAddStaff, pnlStaffUpdate, selectSlot;
	private JLabel[] txfLabel = new JLabel[6];
	private JTextField[] txfFieldStaff = new JTextField[4];
	private JTextField[] txfFieldParking = new JTextField[6];
	private JTextField[] txfField = new JTextField[6];
	private JTextField[] txfFieldUpdate = new JTextField[3];
	private JButton btnAddParkingLot;
	private Parking_Lot_DB lotDb;
	private Space_Booking_DB dbBooking;
	private Staff_DB dbStaff;

	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public Parking_GUI() {
		super("Parking Lot");

		lotDb = new Parking_Lot_DB();
		dbBooking = new Space_Booking_DB();
		dbStaff = new Staff_DB();
		createComponents();
		setVisible(true);
		pack();
		setLocationRelativeTo(null);

	}
    
	/**
	 * Creates panels for our parking lots and other information.
	 */
	private void createComponents()
	{
		pnlButtons = new JPanel();
		btnListLots = new JButton("Parking Lots");
		btnListLots.addActionListener(this);

		pnlButtons = new JPanel();
		btnBookingList = new JButton("Reserved Spots");
		btnBookingList.addActionListener(this);

		btnListStaff = new JButton("Staff Members");
		btnListStaff.addActionListener(this);

		btnSearch = new JButton("Reserve Spot");
		btnSearch.addActionListener(this);

		btnAddStaff = new JButton("Add Staff Member");
		btnAddStaff.addActionListener(this);

		btnAddLot = new JButton("Add Parking Lot");
		btnAddLot.addActionListener(this);

		btnUpdate = new JButton("Update Staff Info");
		btnUpdate.addActionListener(this);

		pnlButtons.add(btnListLots);
		pnlButtons.add(btnBookingList);
		pnlButtons.add(btnListStaff);

		add(pnlButtons, BorderLayout.NORTH);
		
		//List Panel
		pnlContent = new JPanel();

		// Vistor Reserve Panel
		pnlSearch = new JPanel();
		btnReserve = new JButton("Reserve");
		btnReserve.addActionListener(this);
		pnlSearch.setLayout(new GridLayout(9, 0));
		String labelReserveNames[] = {"Booking ID: ", "visitor License Plate: ", "Date: ", "Staff ID: ", "Slot No.: ", "Slot Name: "};
		for (int i = 0; i < labelReserveNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelReserveNames[i]);
			txfField[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfField[i]);
			pnlSearch.add(panel);
		}
		pnlSearch.add(btnReserve);

		
		//Add lot Panel
		pnlAddLot = new JPanel();
		pnlAddLot.setLayout(new GridLayout(6, 0));
		String labelNames[] = {"Parking Lot Name: ", "Location: ", "# Of Covered Slots", "# Of Uncovered Slots", "Floors: ", "Monthly Rate: "};
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelNames[i]);
			txfFieldParking[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfFieldParking[i]);
			pnlAddLot.add(panel);
		}

		//Add Staff Panel
		pnlAddStaff = new JPanel();
		pnlAddStaff.setLayout(new GridLayout(5, 0));
		String labelStaffNames[] = {"Staff ID: ", "Staff Name: ", "Phone Ext: ", "License Plate No: "};
		for (int i = 0; i < labelStaffNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelStaffNames[i]);
			txfFieldStaff[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfFieldStaff[i]);
			pnlAddStaff.add(panel);
		}
		//Update Staff Panel
		pnlStaffUpdate = new JPanel();
		pnlStaffUpdate.setLayout(new GridLayout(4, 0));
		String labelStaffUpdate[] = {"Staff ID: ", "New Phone Ext.: ", "New License Plate: "};
		for (int i = 0; i < labelStaffUpdate.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelStaffUpdate[i]);
			txfFieldUpdate[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfFieldUpdate[i]);
			pnlStaffUpdate.add(panel);
		}


		// More button magic
		btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.addActionListener(this);
		pnlStaffUpdate.add(btnUpdateStaff);

		btnAddStaffConfirm = new JButton("Add Staff");
		btnAddStaffConfirm.addActionListener(this);
		pnlAddStaff.add(btnAddStaffConfirm);

		JPanel panel = new JPanel();
		btnAddParkingLot = new JButton("Add");
		btnAddParkingLot.addActionListener(this);
		panel.add(btnAddParkingLot);
		pnlAddLot.add(panel);

		add(pnlContent, BorderLayout.CENTER);

	}

	/**
	 * The Driver for this program.
	 * @param args Commandline arguments passed into this program that affect functionality.
	 */
	public static void main(String[] args)
	{
		Parking_GUI movieGUI = new Parking_GUI();
		movieGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Event handling to change the panels when different tabs are clicked,
	 * add and search buttons are clicked on the corresponding add and search panels.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Lists all of the lots.
		if (e.getSource() == btnListLots) {
			JPanel lotPanel = new JPanel(new BorderLayout());
			try {
				lotList = lotDb.get_parking_lots();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			data = new Object[lotList.size()][lotColumns.length];
			for (int i = 0; i < lotList.size(); i++) {
				data[i][0] = lotList.get(i).getLotName();
				data[i][1] = lotList.get(i).getLocation();
				data[i][2] = lotList.get(i).getCapacity();
				data[i][3] = lotList.get(i).getFloors();
				data[i][4] = lotList.get(i).getMonthlyRate();
			}
			pnlContent.removeAll();
			table = new JTable(data, lotColumns);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);

			lotPanel.add(scrollPane, BorderLayout.CENTER);

			JPanel btns = new JPanel(new BorderLayout());
			btns.add(btnAddLot, BorderLayout.WEST);
			btns.add(btnSearch, BorderLayout.EAST);

			lotPanel.add(btns, BorderLayout.SOUTH);

			pnlContent.add(lotPanel);
			pnlContent.revalidate();
			this.repaint();

		}
		// Shows all of the visitor reservations.
		else if (e.getSource() == btnBookingList) {
			JPanel container = new JPanel(new BorderLayout());
			JPanel visitorRes = new JPanel(new BorderLayout());
			JPanel staffRes = new JPanel(new BorderLayout());

			JLabel _ = new JLabel("Visitor Reservations:");
			JLabel __ = new JLabel("Staff Reservations:");

			JScrollPane visPain;
			JScrollPane stafPain;

			ArrayList<Space_Booking> visitors = null;
			ArrayList<Staff_Space> staff = null;

			try {
				visitors = Space_Booking_DB.get_bookings();
				staff = Staff_Space_DB.get_staff_spaces();
			} catch(Exception e1) {
				e1.printStackTrace();
			}

			Object visdat[][] = new Object[visitors.size()][space_book_col.length];
			Object stafdat[][] = new Object[staff.size()][staff_space_col.length];

			for(int i = 0; i < visitors.size(); i++) {
				visdat[i][0] = visitors.get(i).getStaffId();
				visdat[i][1] = visitors.get(i).getLotName();
				visdat[i][2] = visitors.get(i).getParkingSlotNo();
				visdat[i][3] = visitors.get(i).getVisitorLisence();
				visdat[i][4] = visitors.get(i).getDateOfVisit().toString();
			}
			for(int i = 0; i < staff.size(); i++) {
				stafdat[i][0] = staff.get(i).getStaffId();
				stafdat[i][1] = staff.get(i).getLotName();
				stafdat[i][2] = staff.get(i).getParkingSlotNo();
			}

			JTable visRes = new JTable(visdat, space_book_col);
			JTable stafRes = new JTable(stafdat, staff_space_col);

			visPain = new JScrollPane(visRes);
			stafPain = new JScrollPane(stafRes);

			visitorRes.add(_, BorderLayout.NORTH);
			visitorRes.add(visPain, BorderLayout.SOUTH);
			staffRes.add(__, BorderLayout.NORTH);
			staffRes.add(stafPain, BorderLayout.SOUTH);
			container.add(visitorRes, BorderLayout.WEST);
			container.add(staffRes, BorderLayout.EAST);

			pnlContent.removeAll();
			pnlContent.add(container);
			pnlContent.revalidate();
			this.repaint();

		}
		// List Staff Screen
		else if(e.getSource() == btnListStaff) {
			JPanel staff = new JPanel(new BorderLayout());
			JPanel staffOptions = new JPanel();
			ArrayList<Staff> staffList = null;
			try {
				staffList = dbStaff.get_staff();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			JLabel staffField = new JLabel("Current Staff Members:", SwingConstants.CENTER);
			data = new Object[staffList.size()][staffColumns.length];
			for (int i = 0; i < staffList.size(); i++) {
				data[i][0] = staffList.get(i).getStaffId();
				data[i][1] = staffList.get(i).getName();
				data[i][2] = staffList.get(i).getPhoneExt();
				data[i][3] = staffList.get(i).getLisencePlateNo();
			}
			pnlContent.removeAll();
			table = new JTable(data, staffColumns);
			JScrollPane staffScroll = new JScrollPane(table);

			staffOptions.add(btnAddStaff);
			staffOptions.add(btnUpdate);

			staff.add(staffField, BorderLayout.NORTH);
			staff.add(staffScroll, BorderLayout.CENTER);
			staff.add(staffOptions, BorderLayout.SOUTH);

			pnlContent.removeAll();
			pnlContent.add(staff);
			pnlContent.revalidate();
			this.repaint();
		}
		// Change to the reservation screen.
		else if (e.getSource() == btnSearch) {
			pnlContent.removeAll();
			// If the user hasn't selected exactly one lot.
			if(table.getSelectedRowCount() != 1) {
				JOptionPane.showMessageDialog(null, "SELECT A LOT YOU IDIOT.");
				pnlContent.revalidate();
				this.repaint();
			}
			int row = table.getSelectedRow();

			String lotName = (String) table.getValueAt(row, 0);
			String lotLoc = (String) table.getValueAt(row, 1);
			pnlContent.add(new Reservation_GUI(lotName, lotLoc));
			pnlContent.revalidate();
			this.repaint();

		}
		// Change to the add lot screen.
		else if (e.getSource() == btnAddLot) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddLot);
			pnlContent.revalidate();
			this.repaint();

		}
		// Change to the add staff screen.
		else if (e.getSource() == btnAddStaff) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddStaff);
			pnlContent.revalidate();
			this.repaint();

		}
		// Change to the Staff update screen.
		else if (e.getSource() == btnUpdate){
			pnlContent.removeAll();
			pnlContent.add(pnlStaffUpdate);
			pnlContent.revalidate();
			this.repaint();

		}
		// Updating staff
		else if (e.getSource() == btnUpdateStaff) {
			try {
				dbStaff.update_staff_license(Integer.parseInt(txfFieldUpdate[0].getText()), txfFieldUpdate[2].getText());
				JOptionPane.showMessageDialog(null, "Staff Updated!");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "BEEP BOOP, U STOOP. . . id");
			}
			try {
				dbStaff.update_staff_extension(Integer.parseInt(txfFieldUpdate[0].getText()), Integer.parseInt(txfFieldUpdate[1].getText()));
				JOptionPane.showMessageDialog(null, "Staff Updated!");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "BEEP BOOP, U STOOP. . . id");
			}
		}
		// Add a visitor booking.
		else if (e.getSource() == btnReserve) {
				Space_Booking reserveSpace = new Space_Booking(Integer.parseInt(txfField[0].getText()), txfField[1].getText(), Date.valueOf(LocalDate.now()), Integer.parseInt(txfField[3].getText()), Integer.parseInt(txfField[4].getText()), txfField[5].getText());
			try {
				dbBooking.add_space_booking(reserveSpace);
				JOptionPane.showMessageDialog(null, "Reservation Successfully booped!");
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Reservation (un)successfully booped!");
			}
			for (int i=0; i<txfField.length; i++) {
				txfField[i].setText("");
			}

		}
		// Add a parking lot.
		else if (e.getSource() == btnAddParkingLot) {
			// Attempt to generate a lot and all of its slots.
			try {
				Complex_Queries.generate_lot(Integer.parseInt(txfFieldParking[2].getText()),
											 Integer.parseInt(txfFieldParking[3].getText()),
											 Integer.parseInt(txfFieldParking[4].getText()),
											 txfFieldParking[0].getText(),
											 txfFieldParking[1].getText(),
											 Float.parseFloat(txfFieldParking[5].getText()));
				JOptionPane.showMessageDialog(null, "Added Successfully!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "YA DONE DIDDLY GOOFED.");
				e1.printStackTrace();
			}
			for (int i=0; i<txfFieldParking.length; i++) {
				txfField[i].setText("");
			}
		}
		else if (e.getSource() == btnAddStaffConfirm) {
			boolean newStaff = true;
			Staff staff = new Staff(Integer.parseInt(txfFieldStaff[0].getText()),
					txfFieldStaff[1].getText(), Integer.parseInt(txfFieldStaff[2].getText()), txfFieldStaff[3].getText());
			try {

				staffList = dbStaff.get_staff();
				for (Staff member :staffList) {
					if (member.getStaffId() == Integer.parseInt(txfFieldStaff[0].getText())) {
						JOptionPane.showMessageDialog(null, "ID already in use!");
						newStaff = false;
					}
				}
				if (newStaff) {
					dbStaff.add_staff(staff);
					JOptionPane.showMessageDialog(null, "Staff Added!");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			for (int i=0; i<txfField.length; i++) {
				txfField[i].setText("");
			}
		}
	}

	/**
	 * Event handling for any cell being changed in the table.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        System.out.println(column);
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
	}

}
