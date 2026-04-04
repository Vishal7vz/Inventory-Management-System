package inventory.management.system;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/Inventory_Management";
    private static final String USER = "root";
    private static final String PASS = "Ravi1234@ravi";

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}