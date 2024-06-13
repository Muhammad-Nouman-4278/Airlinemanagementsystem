package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JourneyDetails extends JFrame implements ActionListener{

    JTable table;
    JTextField ticket;
    JButton show;

    JourneyDetails() {
      
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblticket = new JLabel("Ticket Number:");
        lblticket.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblticket.setBounds(50, 50, 120, 25);
        add(lblticket);
        
        ticket = new JTextField();
        ticket.setBounds(180, 50, 120, 25);
        add(ticket);
        
        show = new JButton("Show Details");
        show.setBackground(new Color(0,100,0));
        show.setForeground(Color.WHITE);
        show.setBounds(320, 50, 150, 25);
        show.addActionListener(this);
        add(show);
        
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 100, 700, 400);
        jsp.setBackground(Color.WHITE);
        add(jsp);
        
        setTitle("Journey Details");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the JFrame on screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
            try {
            	Conn conn = new Conn();
            	 Connection connection = conn.getConnection();
                String ticketNumber = ticket.getText();
                Statement stmt = conn.getStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM booking WHERE ticket_pnr = '" + ticketNumber + "'");

                
                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No Information Found for the provided Ticket Number");
                    return;
                }
                
                DefaultTableModel model = new DefaultTableModel();
                
                
                ResultSetMetaData metaData = rs.getMetaData();
                
               
                int columnCount = metaData.getColumnCount();//Number of Columns
                
                // Add column names to the model
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    model.addColumn(metaData.getColumnName(columnIndex));
                }
            
                while (rs.next())
                {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        rowData[i] = rs.getObject(i + 1);
                    }
                    model.addRow(rowData);
                }
                
                
                table.setModel(model);
            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        new JourneyDetails();
    }
}
