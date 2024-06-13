package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener
{
    private JButton exit, submit, reset;
    private JTextField usertext;
    private JPasswordField passtext;

    public Login() {
        setTitle("Airline Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        
        JLabel welcomeLabel = new JLabel("Welcome to Airline Management System");
        welcomeLabel.setBounds(20, 0, 350, 30);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBackground(new Color(0, 100, 0));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        add(welcomeLabel);

        JLabel namelbl = new JLabel("Username");
        namelbl.setBounds(20, 50, 100, 20);
        add(namelbl);

        JLabel passlbl = new JLabel("Password");
        passlbl.setBounds(20, 110, 100, 20);
        add(passlbl);

        usertext = new JTextField();
        usertext.setBounds(140, 50, 200, 20);
        add(usertext);

        passtext = new JPasswordField();
        passtext.setBounds(140, 110, 200, 20);
        add(passtext);
        reset = new JButton("Reset");
        reset.setBounds(40,150, 100, 20);
        reset.setBackground(new Color(0, 100, 0));
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("Submit");
        submit.setBounds(160, 150, 100, 20);
        submit.setBackground(new Color(0, 100, 0));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        exit = new JButton("Exit");
        exit.setBounds(280, 150, 100, 20);
        exit.setBackground(new Color(0, 100, 0));
        exit.setForeground(Color.WHITE);
        exit.addActionListener(this);
        add(exit);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            usertext.setText("");
            passtext.setText("");
        } 
        else if (e.getSource() == submit) {
            String username = usertext.getText();
            String password = new String(passtext.getPassword());

            try {
                Conn conn = new Conn();
                Connection connection = conn.getConnection();
                String query = "SELECT * FROM login WHERE username = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                 
                    String dbPassword = rs.getString("password"); 
                    
                    if (dbPassword.equals(password)) 
                    {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        new Home();
                        setVisible(false);
                    } 
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
                    }
                    
                } 
                else
                {
                    pstmt.close();
                    rs.close();

                    query = "INSERT INTO login (username, password) VALUES (?, ?)";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "User registered successfully!");
                        new Home();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "User registration failed");
                    }
                }

                pstmt.close();
                connection.close();
            } 
            catch (SQLException ae)
            {
                ae.printStackTrace();
            }
        } else if (e.getSource() == exit) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
