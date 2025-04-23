import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class DBPage implements ActionListener {
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Hello!");
    private Image panelImage,sideImage;
    private JButton addvehicleButton,addproviderButton,addtransportButton,addemployeeButton,addDeliveryButton;

    DBPage()
    {
        try {
            panelImage = ImageIO.read(new File("dbimg.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        welcomeLabel.setBounds(0, 0, 200, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Hello!");

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        // Buttons
        addDeliveryButton = new JButton("Rellenar formato de Entrega");
        addDeliveryButton.setBounds(100,100,300,50);
        addDeliveryButton.setFocusable(false);
        addDeliveryButton.addActionListener(this);

        addvehicleButton = new JButton("Agregar un vehículo");
        addvehicleButton.setBounds(100,230,300,50);
        addvehicleButton.setFocusable(false);
        addvehicleButton.addActionListener(this);

        addtransportButton = new JButton("Rellenar formato de reparto");
        addtransportButton.setBounds(100,165,300,50);
        addtransportButton.setFocusable(false);
        addtransportButton.addActionListener(this);

        addemployeeButton = new JButton("Agregar un empleado");
        addemployeeButton.setBounds(100,295,300,50);
        addemployeeButton.setFocusable(false);
        addemployeeButton.addActionListener(this);

        addproviderButton = new JButton("Agregar un proveedor");
        addproviderButton.setBounds(100,360,300,50);
        addproviderButton.setFocusable(false);
        addproviderButton.addActionListener(this);

        frame.add(addDeliveryButton);
        frame.add(addvehicleButton);
        frame.add(addproviderButton);
        frame.add(addemployeeButton);
        frame.add(addtransportButton);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = frame.getWidth();
                int height = frame.getHeight();
                int newWidth = width / 2;
                int newHeight = height / 2;
                g.drawImage(panelImage, width / 2, height / 2, newWidth, newHeight, this);
            }
        };
        imagePanel.setBounds(0, 0, 900, 700);
        frame.add(imagePanel);
    }

    public static void main(String[] args) {
        new DBPage();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addDeliveryButton){
            DeliveryRegister deliveryRegister = new DeliveryRegister();
        }
        if (e.getSource()==addproviderButton) {
            ProviderRegister providerRegister = new ProviderRegister();
        }
        if (e.getSource()==addemployeeButton) {
            EmployeesRegister employeesRegister = new EmployeesRegister();
        }
        if (e.getSource()==addvehicleButton) {
            VehicleRegister vehicleRegister = new VehicleRegister();
        }
        if (e.getSource()==addtransportButton){
            TransportRegister transportRegister = new TransportRegister();
        }
    }
}
