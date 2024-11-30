package dao;

import connect.DBConnect;
import entity.MonthlyRevenue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public double getRevenueCurrentMonth() {
        try {
            conn = new DBConnect().getConnection();

            // Xác định ngày bắt đầu và ngày kết thúc của tháng hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu tiên của tháng
            Date startDate = calendar.getTime();

            calendar.add(Calendar.MONTH, 1); // Chuyển sang tháng tiếp theo
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Ngày cuối cùng của tháng hiện tại
            Date endDate = calendar.getTime();

            // Format ngày để sử dụng trong câu truy vấn SQL
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strStartDate = dateFormat.format(startDate);
            String strEndDate = dateFormat.format(endDate);

            // Câu truy vấn SQL để lấy tổng doanh thu của tháng hiện tại từ các đơn hàng đã giao thành công
            String sql = "SELECT SUM(total_amount) AS revenue FROM orders "
                    + "WHERE status = 'Đã giao' "
                    + "AND order_date BETWEEN ? AND ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, strStartDate);
            ps.setString(2, strEndDate);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("revenue");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public String getBestSellingProduct() {
        String productName = null;
        String query = """                       
                        select 
                            p.product_name
                        from 
                            Orders o
                        join 
                            Orderdetails od on o.order_id = od.order_id
                        join 
                            ProductSizes ps on od.product_size_id = ps.product_size_id
                        join 
                            Products p on ps.product_id = p.product_id
                        where 
                            o.status = 'đã giao'
                        GROUP BY 
                            p.product_id
                        ORDER BY 
                            SUM(od.quantity) DESC
                        LIMIT 1
                           """;
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                productName = rs.getString("product_name");
            }
        } catch (Exception e) {
        }

        return productName;
    }

    public int getCancelledOrdersCount() {
        int cancelledOrdersCount = 0;
        try {
            String query = "SELECT COUNT(*) AS cancelled_orders_count FROM orders WHERE status = 'hủy'";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                cancelledOrdersCount = rs.getInt("cancelled_orders_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cancelledOrdersCount;
    }

    public List<MonthlyRevenue> getMonthlyRevenue() {
        List<MonthlyRevenue> monthlyRevenueList = new ArrayList<>();
        try {
            conn = new DBConnect().getConnection();
            String query = "SELECT YEAR(order_date) AS order_year, MONTH(order_date) AS order_month, SUM(total_amount) AS monthly_revenue "
                    + "FROM orders "
                    + "WHERE status = 'đã giao' "
                    + "GROUP BY YEAR(order_date), MONTH(order_date) "
                    + "ORDER BY order_year DESC, order_month DESC";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int year = rs.getInt("order_year");
                int month = rs.getInt("order_month");
                double revenue = rs.getDouble("monthly_revenue");
                MonthlyRevenue monthlyRevenue = new MonthlyRevenue(year, month, revenue);
                monthlyRevenueList.add(monthlyRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối, statement, result set nếu cần
        }
        return monthlyRevenueList;
    }

}
