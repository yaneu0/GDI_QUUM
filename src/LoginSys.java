// add package com.javaguides.javaswing.reg;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginSys extends JFrame implements ActionListener {
    private JTextField userIDField;
    private JPasswordField userPasswordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel messageLabel;
    private HashMap<String, String> logincred;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginSys frame = new LoginSys();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginSys() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 420);
        setLayout(null);
        setResizable(false);

        logincred loginCredentials = new logincred();
        logincred = loginCredentials.getLoginInfo();

        JPanel contentPane = new JPanel();
        contentPane.setForeground(new Color(0, 0, 0));
        contentPane.setBackground(new Color(175, 238, 238));
        contentPane.setLayout(null);

        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        userIDField = new JTextField();
        userPasswordField = new JPasswordField();
        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        JLabel userIDLabel = new JLabel("Usuario:");
        JLabel userPasswordLabel = new JLabel("Contraseña:");
        messageLabel = new JLabel("TEST");
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 25));
        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        add(userIDLabel);
        add(userPasswordLabel);
        add(messageLabel);
        add(userIDField);
        add(userPasswordField);
        add(loginButton);
        add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logincred.containsKey(userID)) {
                if (logincred.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Success");
                    DBPage dbPage = new DBPage();
                    dispose();
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("User not found");
            }
        }
    }
}