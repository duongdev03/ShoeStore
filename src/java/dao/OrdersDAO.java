package dao;

import connect.DBConnect;
import entity.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Orders> getAllOrders() {
        List<Orders> list = new ArrayList<>();
        String query = """
                        SELECT 
                            o.order_id,
                            c.customer_name,
                            o.order_date,
                            o.total_amount,
                            o.status,
                            p.method
                        FROM 
                            Orders o
                        JOIN 
                            Customers c ON o.customer_id = c.customer_id
                        JOIN 
                            Payments p ON o.order_id = p.order_id
                        ORDER BY 
                               o.order_date DESC""";

        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Orders(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Orders getOrderByID(String order_id) {
        String query = """
                    SELECT 
                        o.order_id,
                        c.customer_name,
                        o.order_date,
                        o.total_amount,
                        o.status,
                        p.method
                    FROM 
                        Orders o
                    JOIN 
                        Customers c ON o.customer_id = c.customer_id
                    JOIN 
                        Payments p ON o.order_id = p.order_id
                    WHERE 
                        o.order_id = ?""";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, order_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Orders(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));

            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateOrder(String order_id, String status) {
        String query = "update Orders set status = ? where order_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, order_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
