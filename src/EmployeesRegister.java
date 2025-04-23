import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class EmployeesRegister implements ActionListener {
    private Connection connection;
    private  JButton registerButton,resetButton;

    JFrame frame = new JFrame();
    EmployeesRegister() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javadb", "root", "sqlPassword1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        frame.setSize(900, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(300,550,100,50);
        registerButton.setFocusable(false);

        resetButton = new JButton("Reiniciar");
        resetButton.setBounds(500,550,100,50);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(registerButton);
        frame.add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
