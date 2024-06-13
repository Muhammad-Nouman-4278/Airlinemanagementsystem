package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Boarding extends JFrame implements ActionListener {

    JTextField tickettf;
    JLabel nameValueLbl, nationValueLbl, addressValueLbl, sourceValueLbl, destinationValueLbl, flightNameValueLbl, flightCodeValueLbl;
    JButton fetch;
    JLabel ticketlbl, namelbl, nationlbl, addresslbl, sourceLbl, destinationLbl, flightNameLbl, flightCodeLbl;

    Boarding() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        setTitle("Boarding Pass");

        JLabel heading = new JLabel("BOARDING PASS- PIA");
        heading.setBounds(200, 20, 400, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(new Color(0, 100, 0));
        add(heading);

        ticketlbl = new JLabel("Ticket Number");
        ticketlbl.setBounds(60, 80, 150, 25);
        ticketlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(ticketlbl);

        tickettf = new JTextField();
        tickettf.setBounds(200, 80, 150, 25);
        add(tickettf);

        fetch = new JButton("Fetch");
        fetch.setBounds(360, 80, 80, 25);
        fetch.setBackground(new Color(0, 100, 0));
        fetch.setForeground(Color.white);
        fetch.addActionListener(this);
        add(fetch);

        namelbl = new JLabel("Name");
        namelbl.setBounds(60, 130, 150, 25);
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(namelbl);

        nameValueLbl = new JLabel();
        nameValueLbl.setBounds(200, 130, 150, 25);
        nameValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nameValueLbl);

        nationlbl = new JLabel("Nationality");
        nationlbl.setBounds(60, 180, 150, 25);
        nationlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nationlbl);

        nationValueLbl = new JLabel();
        nationValueLbl.setBounds(200, 180, 150, 25);
        nationValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nationValueLbl);

        addresslbl = new JLabel("Address");
        addresslbl.setBounds(60, 230, 150, 25);
        addresslbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(addresslbl);

        addressValueLbl = new JLabel();
        addressValueLbl.setBounds(200, 230, 150, 25);
        addressValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(addressValueLbl);

        sourceLbl = new JLabel("Source");
        sourceLbl.setBounds(60, 280, 150, 25);
        sourceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sourceLbl);

        sourceValueLbl = new JLabel();
        sourceValueLbl.setBounds(200, 280, 150, 25);
        sourceValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sourceValueLbl);

        destinationLbl = new JLabel("Destination");
        destinationLbl.setBounds(60, 330, 150, 25);
        destinationLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(destinationLbl);

        destinationValueLbl = new JLabel();
        destinationValueLbl.setBounds(200, 330, 150, 25);
        destinationValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(destinationValueLbl);

        flightNameLbl = new JLabel("Flight Name");
        flightNameLbl.setBounds(60, 380, 150, 25);
        flightNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(flightNameLbl);

        flightNameValueLbl = new JLabel();
        flightNameValueLbl.setBounds(200, 380, 150, 25);
        flightNameValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(flightNameValueLbl);

        flightCodeLbl = new JLabel("Flight Code");
        flightCodeLbl.setBounds(60, 430, 150, 25);
        flightCodeLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(flightCodeLbl);

        flightCodeValueLbl = new JLabel();
        flightCodeValueLbl.setBounds(200, 430, 150, 25);
        flightCodeValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(flightCodeValueLbl);

        setVisible(true);
        setSize(800, 600);
        setLocation(200, 70);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetch) {
            String ticketNumber = tickettf.getText();
            if (ticketNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a Ticket Number");
                return;
            }

            try {
                Conn conn = new Conn();
                Connection connection = conn.getConnection();
                String query = "select * from booking where ticket_pnr = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, ticketNumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    nameValueLbl.setText(rs.getString("name"));
                    nationValueLbl.setText(rs.getString("nationality"));
                    addressValueLbl.setText(rs.getString("address"));
                    sourceValueLbl.setText(rs.getString("source"));
                    destinationValueLbl.setText(rs.getString("destination"));
                    flightNameValueLbl.setText(rs.getString("flight_name"));
                    flightCodeValueLbl.setText(rs.getString("flight_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No data found for the given Ticket Number");
                }

                rs.close();
                pstmt.close();
            } catch (SQLException ae) {
                ae.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Boarding();
    }
}