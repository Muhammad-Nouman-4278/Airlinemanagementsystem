package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class FlightInfo extends JFrame {
    JTable tbl;

    public FlightInfo() {
        setTitle("Flight Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        String[] columnNames = {"Flight ID", "Flight Name", "Source", "Destination"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tbl = new JTable(model);

        JTableHeader header = tbl.getTableHeader();
        header.setBackground(new Color(0, 100, 0));
        header.setForeground(Color.WHITE);

        
        JScrollPane scrollPane = new JScrollPane(tbl);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        showData();
    }

    private void showData()  
    {
        try {
            Conn conn = new Conn();
            Connection connection = conn.getConnection();
            String query = "SELECT * FROM flight";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tbl.getModel();

            while (rs.next())
            {
                int flightId = rs.getInt("f_code");
                String flightName = rs.getString("f_name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");

                model.addRow(new Object[]{flightId, flightName, source, destination});
            }

            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving flight information: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}