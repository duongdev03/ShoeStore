package entity;

public class orderdetails {
    private int order_detail_id;
    private int order_id;
    private int product_size_id;
    private int quantity;
    private double unit_price;

    public orderdetails() {
    }

    public orderdetails(int order_detail_id, int order_id, int product_size_id, int quantity, double unit_price) {
        this.order_detail_id = order_detail_id;
        this.order_id = order_id;
        this.product_size_id = product_size_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_size_id() {
        return product_size_id;
    }

    public void setProduct_size_id(int product_size_id) {
        this.product_size_id = product_size_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "orderdetails{" + "order_detail_id=" + order_detail_id + ", order_id=" + order_id + ", product_size_id=" + product_size_id + ", quantity=" + quantity + ", unit_price=" + unit_price + '}';
    }
}
