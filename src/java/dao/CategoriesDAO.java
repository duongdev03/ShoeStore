package dao;

import connect.DBConnect;
import entity.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        String query = "SELECT category_id, category_name FROM Categories";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Categories(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void addCategory(String category_name){
        String query = "insert into Categories (category_name) values(?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, category_name);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteCategory(String category_id){
        String query = "delete from Categories where category_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, category_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Categories getCategoryByID(String category_id){
        String query = "select * from Categories where category_id = ?";
        
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, category_id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Categories(rs.getInt(1),
                                      rs.getString(2));
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public void updateCategory(String category_id, String category_name){
        String query = "update Categories set category_name = ? where category_id = ?";
        
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, category_name);
            ps.setString(2, category_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
