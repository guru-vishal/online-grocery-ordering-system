package main.java.Customer;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNo;
    private String customerAddress;

    public Customer(int customerId, String customerName, String customerEmail, String customerPhoneNo, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNo = customerPhoneNo;
        this.customerAddress = customerAddress;
    }

    public Customer(){}

    public int getCustomerId() {
        return customerId;
    }

//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }

    public String getCustomerName() {
        return customerName;
    }

//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }

    public String getCustomerEmail() {
        return customerEmail;
    }

//    public void setCustomerEmail(String customerEmail) {
//        this.customerEmail = customerEmail;
//    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

//    public void setCustomerPhoneNo(String customerPhoneNo) {
//        this.customerPhoneNo = customerPhoneNo;
//    }

    public String getCustomerAddress() {
        return customerAddress;
    }

//    public void setCustomerAddress(String customerAddress) {
//        this.customerAddress = customerAddress;
//    }

    public int login(String phoneEmail){
        try {
            CustomerDBCalls customerDBCalls = new CustomerDBCalls();
            Customer customerData = customerDBCalls.getCustomerByPhoneOrEmail(phoneEmail);

            if (customerData != null) {
                this.customerId = customerData.getCustomerId();
                this.customerName = customerData.getCustomerName();
                this.customerEmail = customerData.getCustomerEmail();
                this.customerPhoneNo = customerData.getCustomerPhoneNo();
                this.customerAddress = customerData.getCustomerAddress();
                return this.customerId;
            } else {
                System.out.println("⚠️ Login failed: No user found with '" + phoneEmail + "'");
            }
        } catch (Exception e) {
            System.out.println("❌ Runtime exception during login:");
//            e.printStackTrace();
        }
        return 0;
    }
}
