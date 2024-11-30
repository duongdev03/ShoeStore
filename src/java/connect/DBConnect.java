package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public Connection getConnection()throws Exception {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/QUANLYBANHANG";
        String user = "root";
        String password = "Duong123@";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args) {
        DBConnect dbContext = new DBConnect();
        try {
            Connection connection = dbContext.getConnection();
            if (connection != null) {
                System.out.println("Kết nối thành công!");
                connection.close();
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
