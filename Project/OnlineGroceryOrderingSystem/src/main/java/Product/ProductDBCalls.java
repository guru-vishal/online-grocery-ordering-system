package main.java.Product;

import main.java.DBConnection.DBTestConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProductDBCalls {
    private final Connection con;

    public ProductDBCalls() {
        this.con = new DBTestConnection().getConnection();
    }

    public void addProduct(String name, String category, double price, int stockQuantity, String description) {
        String query = "INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, category);
            pst.setDouble(3, price);
            pst.setInt(4, stockQuantity);
            pst.setString(5, description);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("main.java.Product.Product added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public void updateStockQuantity(String productName, int newQuantity) {
        String query = "UPDATE Products SET stock_quantity = ? WHERE product_name = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, newQuantity);
            pst.setString(2, productName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stock quantity updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating stock quantity: " + e.getMessage());
        }
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_category"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("product_description")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error listing products: " + e.getMessage());
        }
        return productList;
    }
}
