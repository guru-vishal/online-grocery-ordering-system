package main.java.OrderItems;

import main.java.DBConnection.DBTestConnection;

import java.sql.*;
import java.util.ArrayList;

public class OrderItemsDBCalls {
    private final Connection con;

    public OrderItemsDBCalls() {
        this.con = new DBTestConnection().getConnection();
    }

    public void addOrderItem(int orderId, int productId, int quantity, double price) {
        String query = "INSERT INTO OrderItems (order_id, product_id, quantity, price, total_price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, orderId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);
            pst.setDouble(5, price * quantity);  // Calculate total price
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order item added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding order item: " + e.getMessage());
        }
    }

    public int getOrderItemId(int orderId, int productId, int quantity, double price) {
        String query = "SELECT order_item_id FROM OrderItems WHERE order_id = ? AND product_id = ? AND quantity = ? AND price = ? ORDER BY order_item_id DESC LIMIT 1";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, orderId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("order_item_id");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching order item ID: " + e.getMessage());
        }
        return -1;
    }



    public ArrayList<OrderItems> getOrderItemsByOrderId(int orderId) {
        ArrayList<OrderItems> orderItemList = new ArrayList<>();
        String query = "SELECT * FROM OrderItems WHERE order_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderItems orderItem = new OrderItems(
                        rs.getInt("order_item_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getDouble("total_price")
                );
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving order items: " + e.getMessage());
        }
        return orderItemList;
    }
}
