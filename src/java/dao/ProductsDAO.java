package dao;

import connect.DBConnect;
import entity.Products;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        String query_products = """
                                SELECT 
                                    p.product_id,
                                    p.product_name,
                                    p.description,
                                    p.price,
                                    p.image_url,
                                    c.category_name
                                FROM 
                                    Products p
                                JOIN 
                                    Categories c ON p.category_id = c.category_id""";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query_products);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                                  rs.getString(2),
                                  rs.getString(3),
                                  rs.getDouble(4),
                                  rs.getString(5),
                                  rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void deleteProduct(String product_id){
        String query = "delete from Products where product_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addProduct(String product_name, String description, String price, String image_url, String category_id) {
        String query_add = "INSERT INTO Products (product_name, description, price, image_url, category_id) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query_add);
            ps.setString(1, product_name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, image_url);
            ps.setString(5, category_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    private void closeConnection() {
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
    
    public Products getProductByID(String product_id){
        String query = "select * from Products where product_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Products(rs.getInt(1),
                                  rs.getString(2),
                                  rs.getString(3),
                                  rs.getDouble(4),
                                  rs.getString(5),
                                  rs.getString(6));
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public void updateProduct(String product_id, String product_name, String description, String price, String image_url, String category_id ){
        String query = "update Products set product_name = ?, description = ?, price = ?, image_url = ?, category_id = ? where product_id = ?";
        
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product_name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, image_url);
            ps.setString(5, category_id);
            ps.setString(6, product_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Users login(String username, String password) { 
       String query_product = """
                              select * from users
                              where username = ?
                              and password = ?""";
       
       try {
           conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query_product);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()) {
                return new Users(rs.getInt(1),
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5));
            }
       } catch (Exception e) {
       }
       return null;
   }
   
   public Users checkUsersExits(String username) { 
       String query_product = """
                              select * from Users
                              where username = ?
                              """;
       try {
           conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query_product);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()) {
                return new Users(rs.getInt(1),
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5));
            }
       } catch (Exception e) {
       }
       return null;
   }
   public void signup (String username, String password, String email){ 
       String query_product = "INSERT INTO Users (username, password, email, role)"
                            +"VALUES (?, ?, ?, 'customer')";
       try {
           conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query_product);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
       } catch (Exception e) {
       }
   }
}
