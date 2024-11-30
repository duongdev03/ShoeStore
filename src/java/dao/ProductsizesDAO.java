package dao;

import connect.DBConnect;
import entity.Productsizes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductsizesDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Productsizes> getAllProductsizes() {
        List<Productsizes> list = new ArrayList<>();
        String query = "SELECT ps.product_size_id, p.product_id, p.product_name, ps.size, ps.stock_quantity "
                + "FROM ProductSizes ps "
                + "JOIN Products p ON ps.product_id = p.product_id";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Productsizes(rs.getInt(1),
                                          rs.getString(2),
                                          rs.getString(3),
                                          rs.getString(4),
                                          rs.getInt(5)));
            }
        } catch (Exception e) {
        }

        return list;
    }
    
    public List<Productsizes> getSizesByProductId(String product_id) {
        List<Productsizes> list = new ArrayList<>();
        String query_sizes = """
        SELECT 
            ps.product_size_id, 
            ps.product_id, 
            p.product_name, 
            ps.size, 
            ps.stock_quantity 
        FROM 
            ProductSizes ps
        JOIN 
            Products p ON ps.product_id = p.product_id
        WHERE 
            ps.product_id = ?
    """;
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query_sizes);
            ps.setString(1, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Productsizes(rs.getInt("product_size_id"),
                                          rs.getString("product_id"),
                                          rs.getString("product_name"),
                                          rs.getString("size"),
                                          rs.getInt("stock_quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public void addStockQuantity(String product_id, String size, String stock_quantity){
        String query = "INSERT INTO ProductSizes (product_id, size, stock_quantity) VALUES (?,?,?)";
        try{
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product_id);
            ps.setString(2, size);
            ps.setString(3, stock_quantity);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
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
    
    public void deleteProducsize(String product_size_id){
        String query = "delete from ProductSizes where product_size_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product_size_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Productsizes getProductsizeByID(String product_size_id){
        String query = """
                    SELECT 
                       ps.product_size_id,
                       p.product_id, 
                       p.product_name, 
                       ps.size, 
                       ps.stock_quantity
                    FROM 
                       ProductSizes ps
                    JOIN 
                       Products p ON ps.product_id = p.product_id
                    WHERE 
                       ps.product_size_id = ?""";
        
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product_size_id);
            rs = ps.executeQuery();
            
            while (rs.next()){
                return new Productsizes(rs.getInt(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    public void updateStockQuantity(String product_size_id, int stock_quantity){
        String query = "update ProductSizes set stock_quantity = ? where product_size_id = ?";
        
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, stock_quantity);
            ps.setString(2, product_size_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
