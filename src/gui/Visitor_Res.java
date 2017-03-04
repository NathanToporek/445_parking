package gui;

import complexqueries.Complex_Queries;
import jdk.nashorn.internal.scripts.JO;
import table_types.Space_Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Class that gathers the information necessary for a visitor reservation and
 * tries to make a reservation.
 * @author Nathanael Toporek, Charlton Smith.
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class Visitor_Res extends JPanel implements ActionListener {

    private JTextField staffID, lpn, dov;
    private JLabel slot, lot, staffPre, lpnPre, dovPre;
    private JButton reserve;

    private int slotNo;
    private String lotName, lotLoc;

    /**
     * Constructs this Visitor reservation screen.
     * @param lotName The name of the lot we're reserving in
     * @param lotLoc The location of said lot
     * @param slotNo The slot we're trying to reserve
     */
    public Visitor_Res (String lotName, String lotLoc, int slotNo) {

        this.lotName = lotName;
        this.lotLoc = lotLoc;
        this.slotNo = slotNo;

        createComponents();
        build();
    }
    private void build() {
        JPanel top = new JPanel(new BorderLayout());
        JPanel text = new JPanel(new BorderLayout());
        JPanel sid = new JPanel();
        JPanel vlpn = new JPanel();
        JPanel date = new JPanel();

        sid.add(staffPre);
        sid.add(staffID);

        vlpn.add(lpnPre);
        vlpn.add(lpn);

        date.add(dovPre);
        date.add(dov);

        text.add(sid, BorderLayout.NORTH);
        text.add(vlpn, BorderLayout.CENTER);
        text.add(date, BorderLayout.SOUTH);

        top.add(slot, BorderLayout.NORTH);
        top.add(lot, BorderLayout.CENTER);
        top.add(text, BorderLayout.SOUTH);

        this.add(top, BorderLayout.NORTH);
        this.add(reserve, BorderLayout.SOUTH);
    }
    private void createComponents() {

        slot = new JLabel(String.format("Slot #: %d", slotNo));
        lot = new JLabel(String.format("%s, %s", lotName, lotLoc));
        staffPre = new JLabel("Staff ID: ");
        lpnPre = new JLabel("Visitor License Plate #:");
        dovPre = new JLabel("Date of Visit (yyyy-mm-dd):");

        staffID = new JTextField(25);
        lpn = new JTextField(25);
        dov = new JTextField(25);

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
            try {
                int id = Integer.parseInt(staffID.getText());
                String plateNo = lpn.getText();
                Date visitDate = Date.valueOf(dov.getText());

                Space_Booking sb = new Space_Booking(plateNo, visitDate, id, slotNo, lotName);
                Complex_Queries.reserve_for_guest(sb);
                JOptionPane.showMessageDialog(null, "SUCCESS");
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(null, "Something failed.");
                System.out.print(e1);
            }
        }
    }
}
