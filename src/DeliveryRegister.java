import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class DeliveryRegister implements ActionListener {
    private Connection connection;
    private JButton registerButton, resetButton;
    private JTextField deliveryIDField, itemidField, vehicleidField, dateField, hourField, deladdressField, notesField, startlocationField, employeenameField;
    private JLabel deliveryIDLabel, itemidLabel, vehicleidLabel, dateLabel, hourLabel, deladdressLabel, notesLabel, startlocationLabel, employeenameLabel;

    public DeliveryRegister() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javadb", "root", "sqlPassword1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Registro de entregas");
        frame.setSize(1500, 1000);
        frame.setLayout(null);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(650, 900, 150, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        resetButton = new JButton("Reiniciar");
        resetButton.setBounds(800, 900, 150, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        deliveryIDField = new JTextField();
        deliveryIDField.setBounds(100, 100, 200, 25);
        itemidField = new JTextField();
        itemidField.setBounds(100, 200, 200, 25);
        dateField = new JTextField();
        dateField.setBounds(100, 300, 200, 25);
        hourField = new JTextField();
        hourField.setBounds(100, 400, 200, 25);
        employeenameField = new JTextField();
        employeenameField.setBounds(100, 500, 200, 25);

        startlocationField = new JTextField();
        startlocationField.setBounds(800, 100, 200, 25);
        deladdressField = new JTextField();
        deladdressField.setBounds(800, 200, 200, 25);
        notesField = new JTextField();
        notesField.setBounds(800, 300, 200, 25);

        deliveryIDLabel = new JLabel("ID de Entrega:");
        deliveryIDLabel.setBounds(100, 70, 200, 25);
        itemidLabel = new JLabel("ID del Artículo:");
        itemidLabel.setBounds(100, 170, 200, 25);
        dateLabel = new JLabel("Fecha:(YYYY-MM-DD)");
        dateLabel.setBounds(100, 270, 200, 25);
        hourLabel = new JLabel("Hora: (HH:mm:ss)");
        hourLabel.setBounds(100, 370, 200, 25);
        employeenameLabel = new JLabel("Nombre del Empleado:");
        employeenameLabel.setBounds(100, 470, 200, 25);

        startlocationLabel = new JLabel("Ubicación de Inicio:");
        startlocationLabel.setBounds(800, 70, 200, 25);
        deladdressLabel = new JLabel("Dirección de Entrega:");
        deladdressLabel.setBounds(800, 170, 200, 25);
        notesLabel = new JLabel("Notas:");
        notesLabel.setBounds(800, 270, 200, 25);

        frame.add(registerButton);
        frame.add(resetButton);
        frame.add(deliveryIDField);
        frame.add(itemidField);
        frame.add(dateField);
        frame.add(hourField);
        frame.add(employeenameField);
        frame.add(startlocationField);
        frame.add(deladdressField);
        frame.add(notesField);

        frame.getContentPane().add(deliveryIDLabel);
        frame.getContentPane().add(itemidLabel);
        frame.getContentPane().add(dateLabel);
        frame.getContentPane().add(hourLabel);
        frame.getContentPane().add(employeenameLabel);
        frame.getContentPane().add(startlocationLabel);
        frame.getContentPane().add(deladdressLabel);
        frame.getContentPane().add(notesLabel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DeliveryRegister();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource()==resetButton)){
            deliveryIDField.setText("");
            itemidField.setText("");
            dateField.setText("");
            hourField.setText("");
            employeenameField.setText("");
            startlocationField.setText("");
            deladdressField.setText("");
            notesField.setText("");
        }
        if (e.getSource() == registerButton) {
            try {
                String dateText = dateField.getText();
                try {
                    LocalDate.parse(dateText);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter the date in YYYY-MM-DD format (e.g., 2024-01-08)",
                            "Invalid Date Format",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String timeText = hourField.getText();
                try {
                    LocalTime.parse(timeText);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter the time in HH:mm:ss format (e.g., 14:30:00)",
                            "Invalid Time Format",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String itemIDText = itemidField.getText();
                int itemID = Integer.parseInt(itemIDText);

                String checkItemSQL = "SELECT COUNT(*) FROM RECEIVE WHERE ItemID = ?";
                try (PreparedStatement checkItemStmt = connection.prepareStatement(checkItemSQL)) {
                    checkItemStmt.setInt(1, itemID);
                    ResultSet rs = checkItemStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        JOptionPane.showMessageDialog(null,
                                "The ItemID does not exist in the RECEIVE table.",
                                "Invalid ItemID",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String employeeIDText = employeenameField.getText();
                int employeeID = Integer.parseInt(employeeIDText);

                String checkEmployeeSQL = "SELECT COUNT(*) FROM EMPLOYEE WHERE EmployeeID = ?";
                try (PreparedStatement checkEmployeeStmt = connection.prepareStatement(checkEmployeeSQL)) {
                    checkEmployeeStmt.setInt(1, employeeID);
                    ResultSet rs = checkEmployeeStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        JOptionPane.showMessageDialog(null,
                                "The EmployeeID does not exist in the EMPLOYEE table.",
                                "Invalid EmployeeID",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String vehicleIDText = vehicleidField.getText();
                int vehicleID = Integer.parseInt(vehicleIDText);

                String checkVehicleSQL = "SELECT COUNT(*) FROM TRANSPORT WHERE VehicleID = ?";
                try (PreparedStatement checkVehicleStmt = connection.prepareStatement(checkVehicleSQL)) {
                    checkVehicleStmt.setInt(1, vehicleID);
                    ResultSet rs = checkVehicleStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        JOptionPane.showMessageDialog(null,
                                "The VehicleID does not exist in the TRANSPORT table.",
                                "Invalid VehicleID",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String sql = "INSERT INTO DELIVERY (DeliveryID, ItemID, VehicleID, Date, Hour, DeliveryAddress, Notes, StartLocation, EmployeeID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = connection.prepareStatement(sql);

                pstmt.setInt(1, Integer.parseInt(deliveryIDField.getText())); // DeliveryID
                pstmt.setInt(2, itemID); // ItemID )
                pstmt.setInt(3, vehicleID); // VehicleID
                pstmt.setDate(4, Date.valueOf(dateText)); // Date
                pstmt.setTime(5, Time.valueOf(timeText)); // Hour
                pstmt.setString(6, deladdressField.getText()); // DeliveryAddress
                pstmt.setString(7, notesField.getText()); // Notes
                pstmt.setString(8, startlocationField.getText()); // StartLocation
                pstmt.setInt(9, employeeID); // EmployeeID

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Delivery registered successfully!");
                resetButton.doClick();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "Database Error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Please enter valid numbers for ID fields",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



