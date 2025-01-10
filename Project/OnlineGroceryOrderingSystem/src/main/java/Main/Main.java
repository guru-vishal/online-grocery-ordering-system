package main.java.Main;

import main.java.Customer.Customer;
import main.java.Delivery.Delivery;
import main.java.Delivery.DeliveryDBCalls;
import main.java.Order.Order;
import main.java.OrderItems.OrderItems;
import main.java.Product.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Online Grocery ordering System...");
        Customer customer = new Customer();
        Product product = new Product();
        Order order = new Order();
        OrderItems orderItems;
        Delivery delivery = new Delivery();
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> productArrayList = product.fetchAllProducts();
        ArrayList<Order> orderArrayList;
        boolean flag = true;
        boolean loggedIn = false;
        String phnMail;
        int customerId = 0;

        while (flag) {
            System.out.println("1. Login");
            System.out.println("2. Browse Products");
            System.out.println("3. Place main.java.Order.Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Ordered Items");
            System.out.println("6. Track main.java.Delivery.Delivery");
            System.out.println("7. Logout");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your phone no or email: ");
                    phnMail = sc.nextLine();
                    if (phnMail.equalsIgnoreCase("admin")) {
                        System.out.println("Successfully logged in as admin!");
                        loggedIn = true;
                        while (loggedIn) {
                            System.out.println("1. Show all orders");
                            System.out.println("2. Update order status");
                            System.out.println("3. Add product");
                            System.out.println("4. Update stock");
                            System.out.println("5. Exit");
                            int ch = sc.nextInt();
                            switch (ch) {
                                case 1:
                                    System.out.println(order.getAllOrders());
                                    break;

                                case 2:
                                    System.out.print("Enter order id to update: ");
                                    int orderId = sc.nextInt();
                                    System.out.print("Enter new status to update: ");
                                    String newStatus = sc.nextLine();
                                    order.updateOrderStatus(orderId, newStatus);
                                    break;

                                case 3:
                                    String productName, productCategory;
                                    double price;
                                    int stockQuantity;
                                    String productDescription;
                                    System.out.print("Enter product name: ");
                                    productName = sc.nextLine();
                                    System.out.print("Enter product category: ");
                                    productCategory = sc.nextLine();
                                    System.out.print("Enter price: ");
                                    price = sc.nextDouble();
                                    System.out.print("Enter stock quantity: ");
                                    stockQuantity = sc.nextInt();
                                    System.out.print("Enter description of the product: ");
                                    productDescription = sc.nextLine();
                                    product.addProduct(productName, productCategory, price, stockQuantity, productDescription);
                                    System.out.println("main.java.Product.Product - " + product.getProductName() + " added successfully!");
                                    break;

                                case 4:
                                    String pName;
                                    int sQuantity;
                                    System.out.print("Enter product name: ");
                                    pName = sc.nextLine();
                                    System.out.print("Enter stock quantity: ");
                                    sQuantity = sc.nextInt();
                                    product.updateStockQuantity(pName, sQuantity);
                                    break;

                                default:
                                    loggedIn = false;
                                    break;
                            }
                        }
                    }

                    else {
                        customerId = customer.login(phnMail);
                        if (customerId != 0) {
                            loggedIn = true;
                            System.out.println("Successfully logged in with " + phnMail);
                            System.out.println("Welcome " + customer.getCustomerName());
                        }
                        else {
                            System.out.println("Unable to login!");
                        }
                    }
                    break;

                case 2:
                    product.displayProducts(productArrayList);
                    break;

                case 3:
                    if (loggedIn) {
                        ArrayList<OrderItems> items = new ArrayList<>();
                        System.out.println("Enter product IDs and quantities to add to the order:");

                        double totalPrice;
                        boolean addMore = true;
                        while (addMore) {
                            System.out.print("main.java.Product.Product: ");
                            String productName = sc.nextLine();
                            System.out.print("Quantity: ");
                            int quantity = sc.nextInt();
                            sc.nextLine();

                            Product selectedProduct = findProductByName(productArrayList, productName);
                            if (selectedProduct != null && quantity <= selectedProduct.getStockQuantity()) {
                                totalPrice = selectedProduct.getPrice() * quantity;
                                orderItems = new OrderItems(0, selectedProduct.getProductId(), quantity, selectedProduct.getPrice(), totalPrice);
                                items.add(orderItems);
                            } else {
                                System.out.println("main.java.Product.Product not available or insufficient stock.");
                            }
                            System.out.print("Add more items? (yes/no): ");
                            addMore = sc.nextLine().equalsIgnoreCase("yes");
                        }
                        order.placeOrder(customerId, items);
                        int orderId = order.getOrderId();

                        for(OrderItems orderItem : items) {
                            orderItem.setOrderId(orderId);
                            orderItem.addOrderItem(orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice());
                        }

                        if (delivery != null) {
                            delivery.insertDeliveryDetails(order.getOrderId());
                        }

                    } else {
                        System.out.println("Please login first.");
                    }
                    break;

                case 4:
                    if (loggedIn) {
                        orderArrayList = order.getOrdersByCustomerId(customerId);
                        if (orderArrayList.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            for (Order o : orderArrayList) {
                                System.out.println("main.java.Order.Order ID: " + o.getOrderId() + ", Status: " + o.getOrderStatus() + ", Total Amount: " + o.getTotalAmount());
                            }
                        }
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;

                case 5:
                    if (loggedIn) {
                        System.out.print("Enter order ID to view ordered items: ");
                        int orderId = sc.nextInt();
                        sc.nextLine();

                        ArrayList<OrderItems> orderedItems = OrderItems.getOrderItemsByOrderId(orderId);
                        if (orderedItems.isEmpty()) {
                            System.out.println("No items found for this order.");
                        } else {
                            for (OrderItems item : orderedItems) {
                                System.out.println("main.java.Product.Product ID: " + item.getProductId() + ", Quantity: " + item.getQuantity() + ", Total Price: " + item.getTotalPrice());
                            }
                        }
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;

                case 6:
                    if (loggedIn) {
                        System.out.print("Enter order ID to track delivery: ");
                        int orderId = sc.nextInt();
                        sc.nextLine();
                        delivery = DeliveryDBCalls.fetchDeliveryByOrderId(orderId);
                        if (delivery != null) {
                            delivery.trackDelivery(orderId);
                        } else {
                            System.out.println("main.java.Delivery.Delivery not found.");
                        }
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;

                case 7:
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Successfully logged out");
                    }
                    else {
                        System.out.println("You are already logged out!");
                    }
                    break;

                default:
                    flag = false;
                    System.out.println("Exiting application...");
                    break;
            }
        }

    }

    private static Product findProductByName(ArrayList<Product> products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}
