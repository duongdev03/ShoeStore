
package entity;

import java.util.Date;

public class Orders {
    private int order_id;
    private String customer_id;
    private Date order_date;
    private double total_amount;
    private String status;
    private String payment_id;

    public Orders() {
    }

    public Orders(int order_id, String customer_id, Date order_date, double total_amount, String status, String payment_id) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
        this.payment_id = payment_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    @Override
    public String toString() {
        return "Orders{" + "order_id=" + order_id + ", customer_id=" + customer_id + ", order_date=" + order_date + ", total_amount=" + total_amount + ", status=" + status + ", payment_id=" + payment_id + '}';
    }

    
}
