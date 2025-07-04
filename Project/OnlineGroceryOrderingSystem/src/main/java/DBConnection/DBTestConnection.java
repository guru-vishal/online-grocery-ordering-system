package main.java.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTestConnection {
    Connection con = null;

    String url = "jdbc:mysql://localhost:3306/online_grocery_ordering_system";
    String userName = "root";
    String password = "3016";

    public Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, userName, password);
            }
            catch (Exception e){
                System.out.println("Exception occurred " + e);
            }
        }
        return con;
    }

    public static void main(String[] args) {
        DBTestConnection connection = new DBTestConnection();
        connection.getConnection();
    }
}
