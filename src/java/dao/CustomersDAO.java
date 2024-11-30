package dao;
import connect.DBConnect;
import entity.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class CustomersDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Customers> getAllCustomersWithDetails() {
        List<Customers> list = new ArrayList<>();
        String query = """
                       SELECT
                           c.customer_id,
                           c.user_id,
                           c.customer_name,
                           c.phone_number,
                           c.address,
                           COUNT(DISTINCT o.order_id) AS total_orders,
                           SUM(CASE WHEN o.status = 'Hủy' THEN 1 ELSE 0 END) AS canceled_orders,
                           SUM(CASE WHEN o.status = 'Đã giao' THEN o.total_amount ELSE 0 END) AS total_amount
                       FROM
                           Customers c
                       LEFT JOIN
                           Orders o ON c.customer_id = o.customer_id
                       GROUP BY
                           c.customer_id, c.user_id, c.customer_name, c.phone_number, c.address
                       """;

        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Customers(rs.getInt(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getString(4),
                                       rs.getString(5),
                                       rs.getInt(6),
                                       rs.getInt(7),
                                       rs.getDouble(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public List<Customers> getAllCustomers(){
//        List<Customers> list = new ArrayList<>();
//        String query = "select * from Customers";
//        try {
//            conn = new DBConnect().getConnection();
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Customers(rs.getInt(1),
//                                  rs.getString(2),
//                                  rs.getString(3),
//                                  rs.getString(4),
//                                  rs.getString(5)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
    
}
