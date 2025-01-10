package main.java.Order;

import main.java.OrderItems.OrderItems;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order implements OrderPlacement{
    private int orderId;
    private int customerId;
    private Timestamp orderDate;
    private double totalAmount;
    private String orderStatus;

    public Order(int orderId, int customerId, Timestamp orderDate, double totalAmount, String orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    public Order(){}

    public int getOrderId() {
        return orderId;
    }

//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }

//    public int getCustomerId() {
//        return customerId;
//    }

//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }

//    public Timestamp getOrderDate() {
//        return orderDate;
//    }

//    public void setOrderDate(Timestamp orderDate) {
//        this.orderDate = orderDate;
//    }

    public double getTotalAmount() {
        return totalAmount;
    }

//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }

    public String getOrderStatus() {
        return orderStatus;
    }

//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }

    @Override
    public void placeOrder(int customerId, ArrayList<OrderItems> items) {
        OrderDBCalls orderDBCalls = new OrderDBCalls();

        this.customerId = customerId;
        this.orderStatus = "Pending";
        this.totalAmount = items.stream().mapToDouble(OrderItems::getTotalPrice).sum();
        this.orderDate = new Timestamp(System.currentTimeMillis());

        orderDBCalls.addOrder(customerId, totalAmount, orderStatus);

        this.orderId = orderDBCalls.getLastInsertedOrderId();

        for (OrderItems item : items) {
            System.out.println("main.java.Order.Order item for product ID: " + item.getProductId() + " added.");
        }

        System.out.println("main.java.Order.Order placed successfully with ID: " + this.orderId + " for customer ID: " + this.customerId + " on " + this.orderDate);
        System.out.println("Total amount: Rs." + this.totalAmount);
    }

    public ArrayList<Order> getOrdersByCustomerId(int customerId) {
        OrderDBCalls orderDBCalls = new OrderDBCalls();
        return orderDBCalls.getOrdersByCustomerId(customerId);
    }

    public ArrayList<Order> getAllOrders() {
        OrderDBCalls orderDBCalls = new OrderDBCalls();
        return orderDBCalls.getAllOrders();
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        OrderDBCalls orderDBCalls = new OrderDBCalls();
        orderDBCalls.updateOrderStatus(orderId, newStatus);
        System.out.println("main.java.Order.Order status updated to: " + newStatus);
    }
}
