package airlinemanagementsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class addCustomer extends JFrame implements ActionListener {
    JButton save;
    JTextField nametf, nationtf, cnictf, addresstf, phntf;
    JRadioButton mbtn, fbtn;
    ButtonGroup gender;

    addCustomer() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(250, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(new Color(0, 100, 0));
        add(heading);

        JLabel namelbl = new JLabel("Name");
        namelbl.setBounds(60, 80, 150, 25);
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(namelbl);

        nametf = new JTextField();
        nametf.setBounds(200, 80, 150, 25);
        add(nametf);

        JLabel nationlbl = new JLabel("Nationality");
        nationlbl.setBounds(60, 130, 150, 25);
        nationlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nationlbl);

        nationtf = new JTextField();
        nationtf.setBounds(200, 130, 150, 25);
        add(nationtf);

        JLabel cniclbl = new JLabel("CNIC");
        cniclbl.setBounds(60, 180, 150, 25);
        cniclbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(cniclbl);

        cnictf = new JTextField();
        cnictf.setBounds(200, 180, 150, 25);
        add(cnictf);

        JLabel addresslbl = new JLabel("Address");
        addresslbl.setBounds(60, 230, 150, 25);
        addresslbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(addresslbl);

        addresstf = new JTextField();
        addresstf.setBounds(200, 230, 150, 25);
        add(addresstf);

        JLabel genderlbl = new JLabel("Gender");
        genderlbl.setBounds(60, 280, 150, 25);
        genderlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(genderlbl);

        gender = new ButtonGroup();

        mbtn = new JRadioButton("Male");
        mbtn.setBounds(200, 280, 70, 25);
        add(mbtn);

        fbtn = new JRadioButton("Female");
        fbtn.setBounds(270, 280, 70, 25);
        add(fbtn);

        gender.add(mbtn);
        gender.add(fbtn);

        JLabel phnlbl = new JLabel("Phone");
        phnlbl.setBounds(60, 330, 150, 25);
        phnlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(phnlbl);

        phntf = new JTextField();
        phntf.setBounds(200, 330, 150, 25);
        add(phntf);

        save = new JButton("Save");
        save.setBackground(new Color(0, 100, 0));
        save.setForeground(Color.WHITE);
        save.setBounds(200, 380, 150, 30);
        save.addActionListener(this);
        add(save);

        setVisible(true);
        setSize(800, 600);
        setLocation(200, 70);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            try {
                String name = nametf.getText();
                String nationality = nationtf.getText();
                String cnic = cnictf.getText();
                String address = addresstf.getText();
                String phone = phntf.getText();
                String gender = null;
                if 
                (mbtn.isSelected()) {
                    gender = "Male";
                } else if (fbtn.isSelected()) {
                    gender = "Female";
                }

                if (name.isEmpty() || nationality.isEmpty() || cnic.isEmpty() || address.isEmpty() || phone.isEmpty() || gender == null) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                }
                else
               {
                    Conn c = new Conn();
                    Connection connection= c.getConnection();
                    String query = "INSERT INTO passenger (name, nationality, cnic, address, gender, phone) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, nationality);
                    pstmt.setString(3, cnic);
                    pstmt.setString(4, address);
                    pstmt.setString(5, gender);
                    pstmt.setString(6, phone);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Customer Details added successfully");

                    // Clear text fields
                    nametf.setText("");
                    nationtf.setText("");
                    cnictf.setText("");
                    addresstf.setText("");
                    phntf.setText("");
                  
                }
            } catch (SQLException ae) {
                ae.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new addCustomer();
    }
}
