package main.java.Delivery;

import java.sql.Timestamp;

public class Delivery implements DeliveryTracking{
    private int deliveryId;
    private int orderId;
    private Timestamp deliveryDate;
    private String deliveryStatus;
    private String trackingInfo;

    public Delivery(int deliveryId, int orderId, Timestamp deliveryDate, String deliveryStatus, String trackingInfo) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.trackingInfo = trackingInfo;
    }

    public Delivery(){}

//    public int getDeliveryId() {
//        return deliveryId;
//    }

//    public void setDeliveryId(int deliveryId) {
//        this.deliveryId = deliveryId;
//    }

//    public int getOrderId() {
//        return orderId;
//    }

//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }

//    public Timestamp getDeliverDate() {
//        return deliverDate;
//    }

//    public void setDeliverDate(Timestamp deliverDate) {
//        this.deliverDate = deliverDate;
//    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

//    public void setDeliveryStatus(String deliveryStatus) {
//        this.deliveryStatus = deliveryStatus;
//    }

    public String getTrackingInfo() {
        return trackingInfo;
    }

//    public void setTrackingInfo(String trackingInfo) {
//        this.trackingInfo = trackingInfo;
//    }

    public void insertDeliveryDetails(int orderId){
        this.orderId = orderId;
        DeliveryDBCalls.insertDelivery(orderId);
    }

    @Override
    public void trackDelivery(int orderId){
        this.orderId = orderId;
        this.deliveryStatus = "In Transit";
        this.trackingInfo = "Your order is on the way.";

        System.out.println("Delivery ID: " + this.deliveryId);
        System.out.println("Delivery status for Order ID " + this.orderId + ": " + this.getDeliveryStatus());
        System.out.println("Tracking Info: " + this.getTrackingInfo());
        System.out.println("Expected delivery date: " + deliveryDate);
    }
}
