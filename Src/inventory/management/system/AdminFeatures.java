package inventory.management.system;

import javax.swing.*;
import java.awt.*;

public class AdminFeatures {

    public AdminFeatures() {
        // Frame
        JFrame frame = new JFrame("Inventory Management System - Admin");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon("Src\\Icons\\Logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(14, 9, 41));

        // HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255));
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("Inventory Management System - Admin");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title);
        frame.add(header, BorderLayout.NORTH);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBackground(Color.LIGHT_GRAY);

        String[] menu = {
            "Dashboard",
            "Employee Management",
            "Product/Stock Management",
            "Suppliers",
            "Sales & Purchase",
            "Reports",
            "Log Out"
        };

        JButton btn1 = new JButton(menu[0]);
        btn1.setFont(new Font("Arial", Font.PLAIN, 16));
        btn1.addActionListener(null);
        sidebar.add(btn1);

        JButton btn2 = new JButton(menu[1]);
        btn2.setFont(new Font("Arial", Font.PLAIN, 16));
        btn2.addActionListener(e -> {
            new EmployeeManagementUI();
        });
        sidebar.add(btn2);

        JButton btn3 = new JButton(menu[2]);
        btn3.setFont(new Font("Arial", Font.PLAIN, 16));
        btn3.addActionListener(null);
        sidebar.add(btn3);

        JButton btn4 = new JButton(menu[3]);
        btn4.setFont(new Font("Arial", Font.PLAIN, 16));
        btn4.addActionListener(null);
        sidebar.add(btn4);

        JButton btn5 = new JButton(menu[4]);
        btn5.setFont(new Font("Arial", Font.PLAIN, 16));
        btn5.addActionListener(null);
        sidebar.add(btn5);

        JButton btn6 = new JButton(menu[5]);
        btn6.setFont(new Font("Arial", Font.PLAIN, 16));
        btn6.addActionListener(null);
        sidebar.add(btn6);

        JButton btn7 = new JButton(menu[6]);
        btn7.setFont(new Font("Arial", Font.PLAIN, 16));
        btn7.addActionListener(e -> {
            frame.dispose();
            new LoginFrame();
        });
        sidebar.add(btn7);

        frame.add(sidebar, BorderLayout.WEST);

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        // mainPanel.setLayout(new GridLayout(2, 2, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        frame.add(mainPanel, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel();
        footer.setBackground(Color.GRAY);
        JLabel footText = new JLabel("© 2026 Inventory Management System");
        footText.setForeground(Color.WHITE);
        footer.add(footText);
        frame.add(footer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminFeatures();

    }
}
