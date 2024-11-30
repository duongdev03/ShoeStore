package entity;

import java.time.LocalDateTime;

public class payments {
    private int payment_id;
    private int order_id;
    private LocalDateTime payment_date;
    private double amount;
    private String method; 

    public payments() {
    }

    public payments(int payment_id, int order_id, LocalDateTime payment_date, double amount, String method) {
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.payment_date = payment_date;
        this.amount = amount;
        this.method = method;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "payments{" + "payment_id=" + payment_id + ", order_id=" + order_id + ", payment_date=" + payment_date + ", amount=" + amount + ", method=" + method + '}';
    }
    
}
