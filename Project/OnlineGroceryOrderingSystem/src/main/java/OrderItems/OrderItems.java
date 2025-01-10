package main.java.OrderItems;

import java.util.ArrayList;

public class OrderItems {
    private int orderItemId;
    private int orderId;
    private final int productId;
    private final int quantity;
    private final double price;
    private final double totalPrice;

    public OrderItems(int orderItemId, int orderId, int productId, int quantity, double price, double totalPrice) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public OrderItems(int orderId, int productId, int quantity, double price, double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

//    public int getOrderItemId() {
//        return orderItemId;
//    }

//    public void setOrderItemId(int orderItemId) {
//        this.orderItemId = orderItemId;
//    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

    public int getQuantity() {
        return quantity;
    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public double getPrice() {
        return price;
    }

//    public void setPrice(double price) {
//        this.price = price;
//    }

    public double getTotalPrice() {
        return totalPrice;
    }

//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public static ArrayList<OrderItems> getOrderItemsByOrderId(int orderId) {
        OrderItemsDBCalls orderItemsDBCalls = new OrderItemsDBCalls();
        return orderItemsDBCalls.getOrderItemsByOrderId(orderId);
    }

    public void addOrderItem(int orderId, int productId, int quantity, double price) {
        OrderItemsDBCalls orderItemsDBCalls = new OrderItemsDBCalls();
        orderItemsDBCalls.addOrderItem(orderId, productId, quantity, price);
        System.out.println("Item added successfully to order id: " + this.getOrderId() + " with item id: " + this.orderItemId);
    }
}
