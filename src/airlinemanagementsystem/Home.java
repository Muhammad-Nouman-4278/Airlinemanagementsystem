package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JMenuItem fdetails, cusdetails, journdetails, bookFlight, canticket, bpass;

    Home() 
    {
        setTitle("Airline Management System - Home");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Plane.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1000, 500);
        add(image);

        JLabel heading = new JLabel("Pakistan International Airlines");
        heading.setBounds(50, 20, 900, 100);
        heading.setForeground(new Color(0, 100, 0));
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        image.add(heading);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu details = new JMenu("Details");
        menubar.add(details);

        fdetails = new JMenuItem("Flight Details");
        fdetails.addActionListener(this);
        details.add(fdetails);

        cusdetails = new JMenuItem("Customer Details");
        cusdetails.addActionListener(this);
        details.add(cusdetails);

        journdetails = new JMenuItem("Journey Details");
        journdetails.addActionListener(this);
        details.add(journdetails);

        bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        canticket = new JMenuItem("Cancel Ticket");

        canticket.addActionListener(this);
        details.add(canticket);

        
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);

        bpass = new JMenuItem("Boarding Pass");
        bpass.addActionListener(this);
        ticket.add(bpass);

    
        
       
       

        setLayout(null);
        setSize(1000, 500);
        setLocation(200, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fdetails )
        {
        	new FlightInfo();
        } else if (e.getSource() == cusdetails) {
            new addCustomer();
        } else if (e.getSource() == journdetails) {
            new JourneyDetails();
        } else if (e.getSource() == bookFlight) {
            new BookFlight();
        } else if (e.getSource() == canticket) {
            new Cancel();
        } else if (e.getSource() == bpass) {
          new Boarding();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
