package main.java.Order;

import main.java.OrderItems.OrderItems;

import java.util.ArrayList;

public interface OrderPlacement {
    void placeOrder(int customerId, ArrayList<OrderItems> items);
}
