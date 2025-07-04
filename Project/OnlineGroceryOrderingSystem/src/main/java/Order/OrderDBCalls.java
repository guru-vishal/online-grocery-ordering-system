package main.java.Order;

import main.java.DBConnection.DBTestConnection;

import java.sql.*;
import java.util.ArrayList;

public class OrderDBCalls {
    private final Connection con;

    public OrderDBCalls() {
        this.con = new DBTestConnection().getConnection();
    }

    public void addOrder(int customerId, double totalAmount, String status) {
        String query = "INSERT INTO Orders (customer_id, order_date, total_amount, order_status) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, customerId);
            pst.setDouble(2, totalAmount);
            pst.setString(3, status);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the last inserted order ID
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public ArrayList<Order> getOrdersByCustomerId(int customerId) {
        ArrayList<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE customer_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_status")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving orders: " + e.getMessage());
        }
        return orderList;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        String query = "UPDATE Orders SET order_status = ? WHERE order_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, newStatus);
            pst.setInt(2, orderId);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order status updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating order status: " + e.getMessage());
        }
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_status")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all orders: " + e.getMessage());
        }
        return orderList;
    }

    public int getLastInsertedOrderId() {
        String query = "SELECT LAST_INSERT_ID()";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving last inserted order ID: " + e.getMessage());
        }
        return -1;
    }
}
