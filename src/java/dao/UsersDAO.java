package dao;

import connect.DBConnect;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Users> getUserByAdmin(String role){
        List<Users> list = new ArrayList<>();
        String query = "select * from Users where role = 'admin'";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Users(rs.getInt(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5))); 
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void deleteAccount(String user_id){
        String query = "delete from Users where user_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addAccount(String username, String password, String email, String role){
        String query = "insert into Users (username, password, email, role) values (?, ?, ?, ?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, role);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Users getUserByID(String user_id){
        String query = "select * from Users where user_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
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
    
    public void updateAcccout(String user_id, String password, String email){
        String query = "update Users set password = ?, email = ? where user_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, user_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
