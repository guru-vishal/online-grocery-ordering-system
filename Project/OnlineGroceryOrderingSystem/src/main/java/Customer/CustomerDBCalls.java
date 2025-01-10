package main.java.Customer;

import main.java.DBConnection.DBTestConnection;

import java.sql.*;

public class CustomerDBCalls {
    Connection con = new DBTestConnection().getConnection();

    public Customer getCustomerByPhoneOrEmail(String phoneEmail) {
        String query1 = "SELECT * FROM Customers WHERE customer_phone = ?";
        String query2 = "SELECT * FROM Customers WHERE customer_email = ?";

        try {
            PreparedStatement pst1 = con.prepareStatement(query1);
            pst1.setString(1, phoneEmail);
            ResultSet res1 = pst1.executeQuery();
            if (res1.next()) {
                return new Customer(
                        res1.getInt("customer_id"),
                        res1.getString("customer_name"),
                        res1.getString("customer_email"),
                        res1.getString("customer_phone"),
                        res1.getString("customer_address")
                );
            }

            PreparedStatement pst2 = con.prepareStatement(query2);
            pst2.setString(1, phoneEmail);
            ResultSet res2 = pst2.executeQuery();
            if (res2.next()) {
                return new Customer(
                        res2.getInt("customer_id"),
                        res2.getString("customer_name"),
                        res2.getString("customer_email"),
                        res2.getString("customer_phone"),
                        res2.getString("customer_address")
                );
            }

        } catch (Exception e) {
            System.out.println("Error during login process: " + e.getMessage());
        }

        return null;
    }
}
