package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Cancel extends JFrame implements ActionListener {
    JButton cancel, fetch;
    JTextField cnictf;
    JLabel namelbl, cancelbl, cancellationno, ticket;
    JLabel nameValueLbl, lblfcode, lblcodevalue;
    Random random = new Random();

    JTable cancelledBookingsTable;
    DefaultTableModel tableModel;

    Conn conn;
    Statement s;

    public Cancel() {
        setTitle("Cancel Flight");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("Cancellation");
        heading.setBounds(250, 20, 300, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(new Color(0, 100, 0));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.png"));
        Image image = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(600, 60, 150, 150);
        add(imageLabel);

        tableModel = new DefaultTableModel();
        cancelledBookingsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cancelledBookingsTable);
        scrollPane.setBounds(50, 350, 700, 150);
        add(scrollPane);

        tableModel.addColumn("Ticket Number");
        tableModel.addColumn("Name");
        tableModel.addColumn("Flight Code");
        tableModel.addColumn("Cancellation Number");

        ticket = new JLabel("Ticket Number");
        ticket.setBounds(60, 80, 150, 25);
        ticket.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(ticket);

        cnictf = new JTextField();
        cnictf.setBounds(220, 80, 150, 25);
        add(cnictf);

        fetch = new JButton("Show Details");
        fetch.setBounds(380, 80, 150, 25);
        fetch.setBackground(new Color(0, 100, 0)); 
        fetch.setForeground(Color.white); 
        fetch.addActionListener(this); 
        add(fetch);

        namelbl = new JLabel("Name");
        namelbl.setBounds(60, 130, 150, 25);
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(namelbl);

        nameValueLbl = new JLabel();
        nameValueLbl.setBounds(220, 130, 150, 25);
        nameValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nameValueLbl);

        cancelbl = new JLabel("Cancellation Number");
        cancelbl.setBounds(60, 180, 200, 25);
        cancelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(cancelbl);

        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(270, 180, 150, 25);
        cancellationno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(cancellationno);

        lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 230, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblfcode);

        lblcodevalue = new JLabel();
        lblcodevalue.setBounds(220, 230, 150, 25);
        lblcodevalue.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblcodevalue);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 280, 150, 30);
        cancel.setBackground(new Color(0, 100, 0)); 
        cancel.setForeground(Color.white); 
        cancel.addActionListener(this); 
        add(cancel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Cancel();
    }

    public void actionPerformed(ActionEvent e) {
        conn = new Conn();
        s = conn.s;

        if (e.getSource() == fetch) {
            String cnic = cnictf.getText();

            try {
                String query = "SELECT * FROM booking WHERE ticket_pnr = '" + cnic + "'";
                ResultSet rs = s.executeQuery(query);

                if (rs.next()) {
                    nameValueLbl.setText(rs.getString("name"));
                    lblcodevalue.setText(rs.getString("flight_code"));

                    // Add row to table
                    tableModel.addRow(new String[]{cnic, rs.getString("name"), rs.getString("flight_code"), "" + random.nextInt(1000000)});
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct Ticket Number");
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        } else if (e.getSource() == cancel) {
            String ticketNumber = cnictf.getText();
            String cancellationNumber = cancellationno.getText();
            String name = nameValueLbl.getText();
            String flightCode = lblcodevalue.getText();

            try {
                String deleteQuery = "DELETE FROM booking WHERE ticket_pnr = '" + ticketNumber + "'";
                int rowsAffected = s.executeUpdate(deleteQuery);

                if (rowsAffected > 0) {
                    String insertQuery = "INSERT INTO cancellation (ticket_pnr, name, flight_code, cancellation_number) VALUES ('" + ticketNumber + "', '" + name + "', '" + flightCode + "', '" + cancellationNumber + "')";
                    s.executeUpdate(insertQuery);

                    JOptionPane.showMessageDialog(null, "Flight Cancelled Successfully. Cancellation Number: " + cancellationNumber);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Cancellation Failed. Please check the Ticket Number.");
                }
            } catch 
            (Exception ae) 
            {
                JOptionPane.showMessageDialog(null, "Error: " + ae.getMessage());
                ae.printStackTrace();
            }
        }
    }

}
