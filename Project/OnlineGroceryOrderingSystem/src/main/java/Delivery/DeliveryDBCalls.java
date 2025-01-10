package main.java.Delivery;

import main.java.DBConnection.DBTestConnection;

import java.sql.*;

public class DeliveryDBCalls {

    public static Delivery fetchDeliveryByOrderId(int orderId) {
        Delivery delivery = null;
        String query = "SELECT * FROM Deliveries WHERE order_id = ?";

        try (Connection con = new DBTestConnection().getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int deliveryId = rs.getInt("delivery_id");
                Timestamp deliverDate = rs.getTimestamp("delivery_date");
                String deliveryStatus = rs.getString("delivery_status");
                String trackingInfo = rs.getString("tracking_info");
                delivery = new Delivery(deliveryId, orderId, deliverDate, deliveryStatus, trackingInfo);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching delivery details: " + e.getMessage());
        }

        return delivery;
    }


    public static void insertDelivery(int orderId) {
        String query = "INSERT INTO Deliveries (order_id, delivery_date, delivery_status, tracking_info) VALUES (?, NOW(), ?, ?)";

        try (Connection con = new DBTestConnection().getConnection();
             PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, orderId);
            pst.setString(2, "In transit");
            pst.setString(3, "Your order is on the way");

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                System.out.println("main.java.Delivery.Delivery record inserted with main.java.Delivery.Delivery ID: " + generatedId);
            }

        } catch (SQLException e) {
            System.out.println("Error inserting delivery record: " + e.getMessage());
        }
    }
}
