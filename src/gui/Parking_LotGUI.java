package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import backend.Parking_Lot_DB;
import backend.Space_Booking_DB;
import backend.Staff_DB;
import table_types.Parking_Lot;
import table_types.Space_Booking;
import table_types.Staff;

/**
 * A user interface to view the movies, add a new movie and to update an existing movie.
 * The list is a table with all the movie information in it. The TableModelListener listens to
 * any changes to the cells to modify the values for reach movie.
 * @author mmuppa
 *
 */
public class Parking_LotGUI extends JFrame implements ActionListener, TableModelListener
{
	
	private static final long serialVersionUID = 1779520078061383929L;
	private JButton btnList, btnBookingList, btnSearch, btnAdd, btnAddStaff, btnUpdate, btnAddStaffConfirm, btnUpdateStaff;
	private JPanel pnlButtons, pnlContent;
	private List<Parking_Lot> list;
	private List<Space_Booking> reserveList;
	private List<Staff> staffList;
	private String[] columnNames = {"Lot Name",
            "Location",
            "Capacity",
            "Floors",
            "Monthly Rate"};
	
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch;
	private JButton btnReserve;
	
	private JPanel pnlAdd, pnlAddStaff, pnlStaffUpdate;
	private JLabel[] txfLabel = new JLabel[6];
	private JTextField[] txfFieldStaff = new JTextField[4];
	private JTextField[] txfFieldParking = new JTextField[5];
	private JTextField[] txfField = new JTextField[6];
	private JTextField[] txfFieldUpdate = new JTextField[3];
	private JButton btnAddParkingLot;
	
	private Parking_Lot_DB db;
	private Space_Booking_DB dbBooking;
	private Staff_DB dbStaff;

	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public Parking_LotGUI() {
		super("Parking Lot");

		db = new Parking_Lot_DB();
		dbBooking = new Space_Booking_DB();
		dbStaff = new Staff_DB();
		try
		{
			list = db.get_parking_lots();

			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getLotName();
				data[i][1] = list.get(i).getLocation();
				data[i][2] = list.get(i).getCapacity();
				data[i][3] = list.get(i).getFloors();
				data[i][4] = list.get(i).getMonthlyRate();

			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		createComponents();
		setVisible(true);
		pack();
		setLocationRelativeTo(null);

	}
    
	/**
	 * Creates panels for Movie list, search, add and adds the corresponding 
	 * components to each panel.
	 */
	private void createComponents()
	{
		pnlButtons = new JPanel();
		btnList = new JButton("Parking Lots");
		btnList.addActionListener(this);

		pnlButtons = new JPanel();
		btnBookingList = new JButton("Reserved Spots");
		btnBookingList.addActionListener(this);
		
		btnSearch = new JButton("Reserve Spot");
		btnSearch.addActionListener(this);

		btnAddStaff = new JButton("Add Staff Member");
		btnAddStaff.addActionListener(this);

		btnAdd = new JButton("Add Parking Lot");
		btnAdd.addActionListener(this);

		btnUpdate = new JButton("Update Info");
		btnUpdate.addActionListener(this);

		pnlButtons.add(btnList);
		pnlButtons.add(btnBookingList);
		pnlButtons.add(btnSearch);
		pnlButtons.add(btnAddStaff);
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnUpdate);

		add(pnlButtons, BorderLayout.NORTH);
		
		//List Panel
		pnlContent = new JPanel();
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		pnlContent.add(scrollPane);
		table.getModel().addTableModelListener(this);
		
		//Reserve Panel
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

		
		//Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(6, 0));
		String labelNames[] = {"Parking Lot Name: ", "Location: ", "Capacity: ", "Floors: ", "Monthly Rate: "};
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelNames[i]);
			txfFieldParking[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfFieldParking[i]);
			pnlAdd.add(panel);
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
		pnlAdd.add(panel);

		add(pnlContent, BorderLayout.CENTER);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Parking_LotGUI movieGUI = new Parking_LotGUI();
		movieGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Event handling to change the panels when different tabs are clicked,
	 * add and search buttons are clicked on the corresponding add and search panels.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnList) {
			try {
				list = db.get_parking_lots();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			data = new Object[list.size()][columnNames.length];
			for (int i = 0; i < list.size(); i++) {
				data[i][0] = list.get(i).getLotName();
				data[i][1] = list.get(i).getLocation();
				data[i][2] = list.get(i).getCapacity();
				data[i][3] = list.get(i).getFloors();
				data[i][4] = list.get(i).getMonthlyRate();
			}
			pnlContent.removeAll();
			table = new JTable(data, columnNames);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnBookingList) {
			JPanel reserve = new JPanel();
			try {

				reserveList = dbBooking.get_bookings();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JLabel reserveField = new JLabel("Reservations", SwingConstants.CENTER);

			JLabel reserveDetails[] = new JLabel[reserveList.size()];
			reserve.setLayout(new GridLayout(9, 6));
			reserve.add(reserveField);
			for (int i=0; i < reserveList.size(); i++) {
				JPanel panel = new JPanel();
				reserveDetails[i] = new JLabel(reserveList.get(i).toString());
				panel.add(reserveDetails[i]);
				reserve.add(panel);

			}
			pnlContent.removeAll();
			pnlContent.add(reserve);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnSearch) {
			pnlContent.removeAll();
			pnlContent.add(pnlSearch);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAdd) {
			pnlContent.removeAll();
			pnlContent.add(pnlAdd);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAddStaff) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddStaff);
			pnlContent.revalidate();
			this.repaint();
			//txfFieldStaff

		} else if (e.getSource() == btnUpdate){
			pnlContent.removeAll();
			pnlContent.add(pnlStaffUpdate);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnUpdateStaff) {
			try {
				dbStaff.update_staff_license(Integer.parseInt(txfFieldUpdate[0].getText()), txfFieldUpdate[2].getText());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == btnReserve) {
				Space_Booking reserveSpace = new Space_Booking(Integer.parseInt(txfField[0].getText()), txfField[1].getText(), Date.valueOf(LocalDate.now()), Integer.parseInt(txfField[3].getText()), Integer.parseInt(txfField[4].getText()), txfField[5].getText());
			try {
				dbBooking.add_space_booking(reserveSpace);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Reservation Successfully!");
			for (int i=0; i<txfField.length; i++) {
				txfField[i].setText("");
			}

		} else if (e.getSource() == btnAddParkingLot) {
			Parking_Lot lot = new Parking_Lot(txfFieldParking[0].getText(), txfFieldParking[1].getText()
					,Integer.parseInt(txfFieldParking[2].getText()), Integer.parseInt(txfFieldParking[3].getText()), Float.parseFloat(txfFieldParking[4].getText()));
			try {
				db.add_lot(lot);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
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
		System.out.println("------------------");
		int row = e.getFirstRow();
        int column = e.getColumn();
        System.out.println(column);
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

	}

}
