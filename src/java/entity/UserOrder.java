package entity;

import java.util.Date;

public class UserOrder {
    private int userId;
    private String username;
    private String email;

    // Thông tin đơn hàng và sản phẩm
    private int orderId;
    private Date orderDate;
    private String status;
    private String productId;
    private String productName;
    private String size;
    private int quantity;
    private double unitPrice;
    private String image_url;

    public UserOrder() {}

    public UserOrder(int userId, String username, String email, int orderId, Date orderDate, String status, String productId, String productName, String size, int quantity, double unitPrice, String image_url) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.image_url = image_url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "UserOrder{" + "userId=" + userId + ", username=" + username + ", email=" + email + ", orderId=" + orderId + ", orderDate=" + orderDate + ", status=" + status + ", productId=" + productId + ", productName=" + productName + ", size=" + size + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", image_url=" + image_url + '}';
    }

   
}
