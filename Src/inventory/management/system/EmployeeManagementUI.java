package inventory.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
// import java.sql.*; // ✅ JDBC

public class EmployeeManagementUI extends JFrame implements ActionListener {

    JPanel leftPanel, rightPanel;
    CardLayout cardLayout;

    JButton btnUpdate, btnAdd, btnShow, btnDelete, btnExit;

    // ✅ Database connection
    // Connection con = DBConnection.getConnection();

    // ✅ Table Model (global)
    DefaultTableModel tableModel;
    JTable table;

    public EmployeeManagementUI() {
        setTitle("Employee Management");

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== LEFT PANEL =====
        leftPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.setPreferredSize(new Dimension(250, getHeight()));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        btnUpdate = new JButton("Update");
        btnAdd = new JButton("Add");
        btnShow = new JButton("Show");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");

        JButton[] buttons = { btnUpdate, btnAdd, btnShow, btnDelete, btnExit };

        for (JButton btn : buttons) {
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(200, 60));
            btn.addActionListener(this);
            leftPanel.add(btn);
        }

        add(leftPanel, BorderLayout.WEST);

        // ===== RIGHT PANEL =====
        cardLayout = new CardLayout();
        rightPanel = new JPanel(cardLayout);

        rightPanel.add(createShowPanel(), "SHOW");
        rightPanel.add(createAddPanel(), "ADD");
        rightPanel.add(createUpdatePanel(), "UPDATE");
        rightPanel.add(createDeletePanel(), "DELETE");

        add(rightPanel, BorderLayout.CENTER);

        cardLayout.show(rightPanel, "SHOW");

        setVisible(true);
    }

    // ===== SHOW PANEL =====
    private JPanel createShowPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Show Employees");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columns = {
                "ID", "Name", "Mobile", "Age", "Email",
                "Address", "Gender", "Department", "Designation", "Salary"
        };

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadTableData(); // ✅ load DB data

        return panel;
    }

    // ===== LOAD DATA FROM DB =====
    private void loadTableData() {
        // TODO : load table.
    }

    // ===== ADD PANEL =====
    private JPanel createAddPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Add Employee");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridLayout(10, 2, 20, 20));
        form.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtMobile = new JTextField();
        JTextField txtAge = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtAddress = new JTextField();

        String[] genderOptions = { "Male", "Female", "Other" };
        JComboBox<String> genderBox = new JComboBox<>(genderOptions);

        JTextField txtDepartment = new JTextField();
        JTextField txtDesignation = new JTextField();
        JTextField txtSalary = new JTextField();

        JTextField[] fields = { txtId, txtName, txtMobile, txtAge, txtEmail,
                txtAddress, txtDepartment, txtDesignation, txtSalary };

        for (JTextField field : fields) {
            field.setFont(new Font("Arial", Font.PLAIN, 16));
        }

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Name:"));
        form.add(txtName);
        form.add(new JLabel("Mobile:"));
        form.add(txtMobile);
        form.add(new JLabel("Age:"));
        form.add(txtAge);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);
        form.add(new JLabel("Address:"));
        form.add(txtAddress);
        form.add(new JLabel("Gender:"));
        form.add(genderBox);
        form.add(new JLabel("Department:"));
        form.add(txtDepartment);
        form.add(new JLabel("Designation:"));
        form.add(txtDesignation);
        form.add(new JLabel("Salary:"));
        form.add(txtSalary);

        JPanel btnPanel = new JPanel();
        JButton saveBtn = new JButton("Save");
        JButton resetBtn = new JButton("Reset");

        btnPanel.add(saveBtn);
        btnPanel.add(resetBtn);

        // ✅ SAVE TO DATABASE
        saveBtn.addActionListener(e -> {
            //TODO :  Insert into database 
        });

        // RESET
        resetBtn.addActionListener(e -> {
            txtId.setText("");
            txtName.setText("");
            txtMobile.setText("");
            txtAge.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtDepartment.setText("");
            txtDesignation.setText("");
            txtSalary.setText("");
            genderBox.setSelectedIndex(0);
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ===== UPDATE PANEL =====
    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Update Employee");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridLayout(10, 2, 20, 20));
        form.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtMobile = new JTextField();
        JTextField txtAge = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtAddress = new JTextField();
        String[] genderOptions = { "Male", "Female", "Other" };
        JComboBox<String> genderBox = new JComboBox<>(genderOptions);
        JTextField txtDepartment = new JTextField();
        JTextField txtDesignation = new JTextField();
        JTextField txtSalary = new JTextField();

        JTextField[] fields = { txtId, txtName, txtMobile, txtAge, txtEmail,
                txtAddress, txtDepartment, txtDesignation, txtSalary };

        for (JTextField field : fields) {
            field.setFont(new Font("Arial", Font.PLAIN, 16));
        }

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Name:"));
        form.add(txtName);
        form.add(new JLabel("Mobile:"));
        form.add(txtMobile);
        form.add(new JLabel("Age:"));
        form.add(txtAge);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);
        form.add(new JLabel("Address:"));
        form.add(txtAddress);
        form.add(new JLabel("Gender:"));
        form.add(genderBox);
        form.add(new JLabel("Department:"));
        form.add(txtDepartment);
        form.add(new JLabel("Designation:"));
        form.add(txtDesignation);
        form.add(new JLabel("Salary:"));
        form.add(txtSalary);

        JPanel btnPanel = new JPanel();
        JButton loadBtn = new JButton("Load");
        JButton updateBtn = new JButton("Update");
        JButton resetBtn = new JButton("Reset");

        btnPanel.add(loadBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(resetBtn);

        loadBtn.addActionListener(e -> {
            // TODO : add logic database.
        });

        updateBtn.addActionListener(e -> {
            // TODO : update database logic  
        });

        resetBtn.addActionListener(e -> {
            txtId.setText("");
            txtName.setText("");
            txtMobile.setText("");
            txtAge.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtDepartment.setText("");
            txtDesignation.setText("");
            txtSalary.setText("");
            genderBox.setSelectedIndex(0);
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    // ===== DELETE PANEL =====
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Delete Employee");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridLayout(2, 2, 20, 20));
        form.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JTextField txtId = new JTextField();
        txtId.setFont(new Font("Arial", Font.PLAIN, 16));

        form.add(new JLabel("ID:"));
        form.add(txtId);

        JPanel btnPanel = new JPanel();
        JButton deleteBtn = new JButton("Delete");
        JButton resetBtn = new JButton("Reset");

        btnPanel.add(deleteBtn);
        btnPanel.add(resetBtn);

        deleteBtn.addActionListener(e -> {
            // TODO : delete logic of database.
        });

        resetBtn.addActionListener(e -> txtId.setText(""));

        panel.add(title, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    // ===== SIMPLE PANEL =====
    private JPanel simplePanel(String text) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(label);
        return panel;
    }

    // ===== BUTTON ACTION =====
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnShow) {
            loadTableData(); // refresh when clicked
            cardLayout.show(rightPanel, "SHOW");
        } else if (e.getSource() == btnAdd) {
            cardLayout.show(rightPanel, "ADD");
        } else if (e.getSource() == btnUpdate) {
            cardLayout.show(rightPanel, "UPDATE");
        } else if (e.getSource() == btnDelete) {
            cardLayout.show(rightPanel, "DELETE");
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new EmployeeManagementUI();
    }
}