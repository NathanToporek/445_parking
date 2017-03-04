package gui;

import backend.Parking_Slot_DB;
import complexqueries.Complex_Queries;
import table_types.Parking_Slot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class to handle the first steps of reserving a parking slot. Namely, getting the slot number
 * and then directing the user to another screen based off of the type of reservation they're making.
 *
 * @author Nathanael Toporek, Charlton Smith.
 * @version 1.0.0.0.0.0.0.0.0.0.0
 */
public class Reservation_GUI extends JPanel implements ActionListener {

    private JButton nextStep;
    private ButtonGroup radiogroup;
    private JScrollPane slotScroller;
    private JRadioButton guest, staff;
    private JLabel selectSlot, ourLot, resType;
    private JTable slots;

    private String lotName, lotLoc;
    private String slotCols[] = {"Slot #"};

    /**
     * Constructor for a Reservation screen. This will get all the available slots
     * in the specified lot and display them for the user.
     * @param lName The lot name
     * @param lLoc The location of the lot.
     */
    public Reservation_GUI(String lName, String lLoc) {
        super(new BorderLayout());

        lotName = lName;
        lotLoc = lLoc;

        createComponents();
        getSlots();
        build();
    }
    private void build() {
        JPanel top = new JPanel(new BorderLayout());
        JPanel bot = new JPanel(new BorderLayout());
        JPanel botmid = new JPanel(new BorderLayout());

        top.add(selectSlot, BorderLayout.NORTH);
        top.add(ourLot, BorderLayout.CENTER);
        slotScroller = new JScrollPane(slots);
        top.add(slotScroller, BorderLayout.SOUTH);
        botmid.add(guest, BorderLayout.NORTH);
        botmid.add(staff, BorderLayout.SOUTH);

        bot.add(resType, BorderLayout.NORTH);
        bot.add(botmid, BorderLayout.CENTER);
        bot.add(nextStep, BorderLayout.SOUTH);

        this.add(top, BorderLayout.NORTH);
        this.add(bot, BorderLayout.SOUTH);
    }
    private void getSlots() {
        try {
            ArrayList<Parking_Slot> slotList = Complex_Queries.available_slots_by_lot_name(lotName);
            Object data[][] = new Object[slotList.size()][1];
            for (int i = 0; i < slotList.size(); i++) {
                data[i][0] = slotList.get(i).getSlotNo();
            }
            slots = new JTable(data, slotCols);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
    private void createComponents() {
        selectSlot = new JLabel("Select Slot #:");
        ourLot = new JLabel(String.format("%s, %s", lotName, lotLoc));
        resType = new JLabel("Reservation Type:");

        nextStep = new JButton("Next");
        nextStep.addActionListener(this);

        radiogroup = new ButtonGroup();
        guest = new JRadioButton("Guest");
        staff = new JRadioButton("Staff");

        radiogroup.add(guest);
        radiogroup.add(staff);
    }

    /**
     * Event handling to change the panels when different tabs are clicked,
     * add and search buttons are clicked on the corresponding add and search panels.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextStep) {

            if(slots.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(null, "SELECT SOMETHING, YOU INDECISIVE BAFOON.");
            } else {
                this.removeAll();
                int row = slots.getSelectedRow();
                int slotNo = (int) slots.getValueAt(row, 0);
                if(guest.isSelected()) {
                    this.add(new Visitor_Res(lotName, lotLoc, slotNo));
                } else if(staff.isSelected()) {
                    this.add(new Staff_Res(lotName, lotLoc, slotNo));
                } else {
                    JOptionPane.showMessageDialog(null, "SELECT SOMETHING, YOU INDECISIVE BAFOON.");
                }
            }
            this.revalidate();
            this.repaint();
        }
    }
}
