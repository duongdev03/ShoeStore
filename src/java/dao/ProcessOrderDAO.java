package dao;

import connect.DBConnect;
import entity.*;

import java.sql.*;
import java.util.List;

public class ProcessOrderDAO {

    public int insertFullOrder(Customers customer, Orders order, payments payment, List<orderdetails> details) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // 1. Insert customer
            String sqlCustomer = "INSERT INTO customers (user_id, customer_name, phone_number, address) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getUser_id());
            ps.setString(2, customer.getCustomer_name());
            ps.setString(3, customer.getPhone_number());
            ps.setString(4, customer.getAddress());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int customerId = -1;
            if (rs.next()) {
                customerId = rs.getInt(1);
            }
            rs.close();
            ps.close();

            // 2. Insert order
            String sqlOrder = "INSERT INTO orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerId);
            ps.setObject(2, order.getOrder_date());
            ps.setDouble(3, order.getTotal_amount());
            ps.setString(4, order.getStatus());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            rs.close();
            ps.close();

            // 3. Insert payment
            String sqlPayment = "INSERT INTO payments (order_id, payment_date, amount, method) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sqlPayment);
            ps.setInt(1, orderId);
            ps.setObject(2, payment.getPayment_date());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getMethod());
            ps.executeUpdate();
            ps.close();

            // 4. Insert order details + update stock
            String insertDetailSql = "INSERT INTO OrderDetails (order_id, product_size_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
            String updateStockSql = "UPDATE ProductSizes SET stock_quantity = stock_quantity - ? WHERE product_size_id = ? AND stock_quantity >= ?";
            PreparedStatement psInsertDetail = conn.prepareStatement(insertDetailSql);
            PreparedStatement psUpdateStock = conn.prepareStatement(updateStockSql);

            for (orderdetails od : details) {
                // Kiểm tra và cập nhật tồn kho
                psUpdateStock.setInt(1, od.getQuantity());
                psUpdateStock.setInt(2, od.getProduct_size_id());
                psUpdateStock.setInt(3, od.getQuantity());
                int affectedRows = psUpdateStock.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    throw new SQLException("Không đủ tồn kho cho sản phẩm có ID: " + od.getProduct_size_id());
                }

                // Thêm chi tiết đơn hàng
                psInsertDetail.setInt(1, orderId);
                psInsertDetail.setInt(2, od.getProduct_size_id());
                psInsertDetail.setInt(3, od.getQuantity());
                psInsertDetail.setDouble(4, od.getUnit_price());
                psInsertDetail.executeUpdate();
            }

            psInsertDetail.close();
            psUpdateStock.close();

            conn.commit(); //  Hoàn tất transaction
            return orderId;

        } catch (Exception e) {
            if (conn != null) conn.rollback(); //  Nếu lỗi thì rollback
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.setAutoCommit(true); // Reset lại chế độ auto
            if (conn != null) conn.close();
        }
    }
}
