package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.*;

public class BookFlight extends JFrame implements ActionListener {
    JButton save, fetch, flight;
    JTextField cnictf;
    JLabel namelbl, nationlbl, addresslbl, phnlbl, cniclbl, sourceLbl, destinationLbl;
    JLabel lblfname, lblfcode; // New labels for flight name and code
    JLabel nameValueLbl, nationValueLbl, addressValueLbl, genderValueLbl;
    JLabel lblfnamevalue, lblcodevalue; // Values for flight name and code
    Choice source, destination;

    BookFlight() {

        setTitle("Book Flight");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(200, 20, 300, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(new Color(0, 100, 0));
        add(heading);

        cniclbl = new JLabel("CNIC");
        cniclbl.setBounds(60, 80, 150, 25);
        cniclbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(cniclbl);

        cnictf = new JTextField();
        cnictf.setBounds(200, 80, 150, 25);
        add(cnictf);

        fetch = new JButton("Fetch");
        fetch.setBounds(360, 80, 80, 25);
        fetch.setBackground(new Color(0, 100, 0)); // Set background color to green
        fetch.setForeground(Color.white); // Set text color to white
        fetch.addActionListener(this); // Add action listener
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

        JLabel genderlbl = new JLabel("Gender");
        genderlbl.setBounds(60, 280, 150, 25);
        genderlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(genderlbl);

        genderValueLbl = new JLabel();
        genderValueLbl.setBounds(200, 280, 150, 25);
        genderValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(genderValueLbl);

        sourceLbl = new JLabel("Source");
        sourceLbl.setBounds(60, 330, 150, 25);
        sourceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sourceLbl);

        source = new Choice();
        source.setBounds(230, 330, 150, 25);
        add(source);

        destinationLbl = new JLabel("Destination");
        destinationLbl.setBounds(60, 380, 150, 25);
        destinationLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(destinationLbl);

        destination = new Choice();
        destination.setBounds(230, 380, 150, 25);
        add(destination);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM flight";
            ResultSet rc = c.s.executeQuery(query);

            while (rc.next()) {
                source.add(rc.getString("source"));
                destination.add(rc.getString("destination"));
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        }

        flight = new JButton("Fetch Flight");
        flight.setBounds(400, 380, 120, 25);
        flight.setBackground(new Color(0, 100, 0)); // Set background color to green
        flight.setForeground(Color.white); // Set text color to white
        flight.addActionListener(this);
        add(flight);

        save = new JButton("Book Flight");
        save.setBounds(250, 520, 150, 30);
        save.setBackground(new Color(0, 100, 0)); // Set background color to green
        save.setForeground(Color.white); // Set text color to white
        save.addActionListener(this); // Add action listener
        add(save);

        // New labels and their values under the "Destination" label
        lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblfname);

        lblfnamevalue = new JLabel();
        lblfnamevalue.setBounds(200, 430, 150, 25);
        lblfnamevalue.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblfnamevalue);

        lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblfcode);

        lblcodevalue = new JLabel();
        lblcodevalue.setBounds(200, 480, 150, 25);
        lblcodevalue.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblcodevalue);

        // Adjusted image size
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        Image image = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel imagelabel = new JLabel(i2);
        imagelabel.setBounds(550, 80, 500, 300);
        add(imagelabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BookFlight();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetch) {
            String cnic = cnictf.getText();

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM passenger WHERE cnic = '" + cnic + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    nameValueLbl.setText(rs.getString("name"));
                    nationValueLbl.setText(rs.getString("nationality"));
                    addressValueLbl.setText(rs.getString("address"));
                    genderValueLbl.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct CNIC");
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        } else if (e.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM flight WHERE source = '" + src + "' and destination = '" + dest + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    lblfnamevalue.setText(rs.getString("f_name")); // Correct label to set the flight name value
                    lblcodevalue.setText(rs.getString("f_code"));  // Correct label to set the flight code value
                } else {
                    JOptionPane.showMessageDialog(null, "No Data for Flight");
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        } else if (e.getSource() == save)
        
        {
        	Random random = new Random();
        	
        	
            String cnic = cnictf.getText();
            String name = nameValueLbl.getText();
            String nationality = nationValueLbl.getText();
            String address = addressValueLbl.getText();
            String gender = genderValueLbl.getText();
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            String flightName = lblfnamevalue.getText();
            String flightCode = lblcodevalue.getText();

            // Validate all fields before proceeding
            if (cnic.isEmpty() || name.isEmpty() || nationality.isEmpty() || address.isEmpty() || gender.isEmpty() || src.isEmpty() || dest.isEmpty() || flightName.isEmpty() || flightCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                return;
            }

            try {
                Conn conn = new Conn();
                
                String ticketPNR = "PNR-" + random.nextInt(1000000); 
                String query = "INSERT INTO booking (cnic, name, nationality, address, gender, source, destination, flight_name, flight_code, ticket_pnr) VALUES ('" + cnic + "', '" + name + "', '" + nationality + "', '" + address + "', '" + gender + "', '" + src + "', '" + dest + "', '" + flightName + "', '" + flightCode + "', '" + ticketPNR + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Flight Booked Successfully. Ticket Number: " + ticketPNR);
                setVisible(false);
            } catch (Exception ae) {
                JOptionPane.showMessageDialog(null, "Error: " + ae.getMessage());
                ae.printStackTrace();
            }
        }
    }
}
