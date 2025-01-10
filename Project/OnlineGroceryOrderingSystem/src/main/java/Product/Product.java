package main.java.Product;

import java.util.ArrayList;

public class Product {
    private int productId;
    private String productName;
    private String productCategory;
    private double price;
    private int stockQuantity;
    private String productDescription;

    public Product(int productId, String productName, String productCategory, double price, int stockQuantity, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productDescription = productDescription;
    }

    public Product(){}

    public int getProductId() {
        return productId;
    }

//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

    public String getProductName() {
        return productName;
    }

//    public void setProductName(String productName) {
//        this.productName = productName;
//    }

    public String getProductCategory() {
        return productCategory;
    }

//    public void setProductCategory(String productCategory) {
//        this.productCategory = productCategory;
//    }

    public double getPrice() {
        return price;
    }

//    public void setPrice(double price) {
//        this.price = price;
//    }

    public int getStockQuantity() {
        return stockQuantity;
    }

//    public void setStockQuantity(int stockQuantity) {
//        this.stockQuantity = stockQuantity;
//    }

    public String getProductDescription() {
        return productDescription;
    }

//    public void setProductDescription(String productDescription) {
//        this.productDescription = productDescription;
//    }

    public ArrayList<Product> fetchAllProducts() {
        ProductDBCalls productDBCalls = new ProductDBCalls();
        return productDBCalls.getAllProducts();
    }

    public void displayProducts(ArrayList<Product> products) {
        System.out.println("Available Products:");
        for (Product p : products) {
            System.out.println(p.getProductId() + ": " + p.getProductName() + " - " + p.getProductCategory() + " - Rs." + p.getPrice() + " (Stock: " + p.getStockQuantity() + ")");
            System.out.println("Description: " + p.getProductDescription());
        }
    }

    public void addProduct(String productName, String productCategory, double price, int stockQuantity, String productDescription) {
        ProductDBCalls productDBCalls = new ProductDBCalls();
        productDBCalls.addProduct(productName,productCategory, price, stockQuantity, productDescription);
    }

    public void updateStockQuantity(String productName, int stockQuantity) {
        ProductDBCalls productDBCalls = new ProductDBCalls();
        productDBCalls.updateStockQuantity(productName, stockQuantity);
    }
}
