package dao;

import entity.OrderDetail;
import connect.DBConnect;
import entity.UserOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO {

    // Lấy danh sách chi tiết đơn hàng theo order_id
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws Exception {
        List<OrderDetail> orderDetails = new ArrayList<>();

        String sql = """
            SELECT 
                o.order_id, 
                c.customer_name, 
                c.address, 
                c.phone_number, 
                CONCAT(p.product_name, ', size ', ps.size, ', số lượng ', od.quantity) AS product_details, 
                o.order_date, 
                pm.method AS payment_method, 
                CASE 
                    WHEN pm.method = 'Chuyển khoản' THEN 0
                    ELSE o.total_amount
                END AS total_amount
            FROM Orders o
            JOIN Customers c ON o.customer_id = c.customer_id
            JOIN OrderDetails od ON o.order_id = od.order_id
            JOIN ProductSizes ps ON od.product_size_id = ps.product_size_id
            JOIN Products p ON ps.product_id = p.product_id
            JOIN Payments pm ON o.order_id = pm.order_id
            WHERE o.order_id = ?;
        """;

        try (Connection conn = new DBConnect().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Tạo một đối tượng OrderDetail mới với các thông tin lấy từ ResultSet
                    OrderDetail detail = new OrderDetail();
                    detail.setOrderId(rs.getInt("order_id"));
                    detail.setCustomerName(rs.getString("customer_name"));
                    detail.setAddress(rs.getString("address"));
                    detail.setPhoneNumber(rs.getString("phone_number"));
                    detail.setProductDetails(rs.getString("product_details"));
                    detail.setOrderDate(rs.getDate("order_date"));
                    detail.setPaymentMethod(rs.getString("payment_method"));
                    detail.setTotalAmount(rs.getDouble("total_amount"));

                    // Thêm vào danh sách chi tiết đơn hàng
                    orderDetails.add(detail);
                }
            }
        }
        return orderDetails;
    }

    // lấy danh sách đơn hàng theo user_id
    public List<UserOrder> getUserOrders(int userId) throws Exception {
        List<UserOrder> orders = new ArrayList<>();

        // Truy vấn thông tin người dùng
        String userSql = "SELECT username, email FROM Users WHERE user_id = ?";

        // Truy vấn thông tin đơn hàng của user
        String orderSql = """
        SELECT 
            o.order_id,
            o.order_date,
            o.status,
            p.product_name,
            p.product_id,
            ps.size,
            od.quantity,
            od.unit_price,
            u.username,
            u.email,
            p.image_url
        FROM Orders o
        JOIN OrderDetails od ON o.order_id = od.order_id
        JOIN ProductSizes ps ON od.product_size_id = ps.product_size_id
        JOIN Products p ON ps.product_id = p.product_id
        JOIN Customers c ON o.customer_id = c.customer_id
        JOIN Users u ON c.user_id = u.user_id
        WHERE u.user_id = ?
        ORDER BY o.order_date DESC;
    """;

        try (Connection conn = new DBConnect().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(orderSql)) {
                ps.setInt(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        UserOrder order = new UserOrder();
                        order.setUserId(userId);
                        order.setUsername(rs.getString("username"));
                        order.setEmail(rs.getString("email"));
                        order.setOrderId(rs.getInt("order_id"));
                        order.setOrderDate(rs.getDate("order_date"));
                        order.setStatus(rs.getString("status"));
                        order.setProductId(rs.getString("product_id"));
                        order.setProductName(rs.getString("product_name"));
                        order.setSize(rs.getString("size"));
                        order.setQuantity(rs.getInt("quantity"));
                        order.setUnitPrice(rs.getDouble("unit_price"));
                        order.setImage_url(rs.getString("image_url"));

                        orders.add(order);
                    }
                }
            }
        }

        return orders;
    }

}
