package gui;

import complexqueries.Complex_Queries;
import table_types.Staff_Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to gather information for a staff slot reservation, which it uses to
 * try and make a reservation.
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Staff_Res extends JPanel implements ActionListener {

    private JTextField staffID;
    private JLabel slot, lot, staffPre;
    private JButton reserve;

    private int slotNo;
    private String lotName, lotLoc;

    /**
     * Constructs this view for the user.
     * @param lotName The name of the lot we're reserving in.
     * @param lotLoc The location of the aforementioned lot.
     * @param slotNo The slot in the lot that we're trying to reserve.
     */
    public Staff_Res (String lotName, String lotLoc, int slotNo) {
        super(new BorderLayout());

        this.lotName = lotName;
        this.lotLoc = lotLoc;
        this.slotNo = slotNo;

        createComponents();
        build();
    }

    private void build() {

        JPanel top = new JPanel(new BorderLayout());
        JPanel topBot = new JPanel();

        top.add(slot, BorderLayout.NORTH);
        top.add(lot, BorderLayout.CENTER);

        topBot.add(staffPre);
        topBot.add(staffID);

        top.add(topBot, BorderLayout.SOUTH);

        this.add(top, BorderLayout.NORTH);
        this.add(reserve, BorderLayout.SOUTH);
    }

    private void createComponents() {

        slot = new JLabel(String.format("Slot #: %d", slotNo));
        lot = new JLabel(String.format("%s, %s", lotName, lotLoc));
        staffPre = new JLabel("Staff ID: ");

        staffID = new JTextField(25);

        reserve = new JButton("Reserve!");
        reserve.addActionListener(this);
    }

    /**
     * Event handling to change the panels when different tabs are clicked,
     * add and search buttons are clicked on the corresponding add and search panels.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == reserve) {

            int id = -1;
            Staff_Space space = null;
            try {
                id = Integer.parseInt(staffID.getText());
                space = new Staff_Space(id, slotNo, lotName);
                Complex_Queries.reserve_for_staff(space);
                JOptionPane.showMessageDialog(null, "SUCCESS.");
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
                JOptionPane.showMessageDialog(null, "Bad Id");
                staffID.setText("");
                this.repaint();
            }
        }
    }
}
