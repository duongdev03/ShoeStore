package entity;


public class Customers {
    private int customer_id;
    private String user_id;
    private String customer_name;
    private String phone_number;
    private String address;
    private int totalOrders;
    private int cancelledOrders;
    private double totalAmount;

    public Customers() {
    }

    public Customers(int customer_id, String user_id, String customer_name, String phone_number, String address, int totalOrders, int cancelledOrders, double totalAmount) {
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.customer_name = customer_name;
        this.phone_number = phone_number;
        this.address = address;
        this.totalOrders = totalOrders;
        this.cancelledOrders = cancelledOrders;
        this.totalAmount = totalAmount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(int cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Customers{" + "customer_id=" + customer_id + ", user_id=" + user_id + ", customer_name=" + customer_name + ", phone_number=" + phone_number + ", address=" + address + ", totalOrders=" + totalOrders + ", cancelledOrders=" + cancelledOrders + ", totalAmount=" + totalAmount + '}';
    }

    
}
