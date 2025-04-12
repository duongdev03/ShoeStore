package dao;

import connect.DBConnect;
import entity.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchProductDAO {

    public List<Products> searchProducts(String query, String category, String price, String sort, int page) {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE product_name LIKE ?";
        
        // Thêm điều kiện danh mục
        if (category != null && !category.isEmpty()) {
            sql += " AND category_id = ?";
        }
        
        // Thêm điều kiện khoảng giá
        if (price != null && !price.isEmpty()) {
            String[] range = price.split("-");
            if (range.length == 2) {
                sql += " AND price BETWEEN ? AND ?";
            } else if (range.length == 1) {
                sql += " AND price >= ?";
            }
        }
        
        // Thêm điều kiện sắp xếp
        if (sort != null && !sort.isEmpty()) {
            if (sort.equals("price-asc")) {
                sql += " ORDER BY price ASC";
            } else if (sort.equals("price-desc")) {
                sql += " ORDER BY price DESC";
            }
        }
        
        // Thêm phân trang
        sql += " LIMIT ?, 12"; // 12 sản phẩm mỗi trang

        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, "%" + query + "%"); // Tìm kiếm theo từ khóa
            
            // Gán giá trị cho danh mục
            if (category != null && !category.isEmpty()) {
                ps.setString(index++, category);
            }
            
            // Gán giá trị cho khoảng giá
            if (price != null && !price.isEmpty()) {
                String[] range = price.split("-");
                if (range.length == 2) {
                    ps.setDouble(index++, Double.parseDouble(range[0]));
                    ps.setDouble(index++, Double.parseDouble(range[1]));
                } else if (range.length == 1) {
                    ps.setDouble(index++, Double.parseDouble(range[0]));
                }
            }
            
            // Gán giá trị cho phân trang (offset)
            ps.setInt(index, (page - 1) * 12);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Products product = new Products();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage_url(rs.getString("image_url"));
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    

    public int getTotalPages(String query, String category, String price) {
        int totalPages = 0;
        String sql = "SELECT COUNT(*) FROM Products WHERE product_name LIKE ?";
        
        // Thêm điều kiện danh mục
        if (category != null && !category.isEmpty()) {
            sql += " AND category_id = ?";
        }
        
        // Thêm điều kiện khoảng giá
        if (price != null && !price.isEmpty()) {
            String[] range = price.split("-");
            if (range.length == 2) {
                sql += " AND price BETWEEN ? AND ?";
            } else if (range.length == 1) {
                sql += " AND price >= ?";
            }
        }

        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, "%" + query + "%"); // Tìm kiếm theo từ khóa
            
            // Gán giá trị cho danh mục
            if (category != null && !category.isEmpty()) {
                ps.setString(index++, category);
            }
            
            // Gán giá trị cho khoảng giá
            if (price != null && !price.isEmpty()) {
                String[] range = price.split("-");
                if (range.length == 2) {
                    ps.setDouble(index++, Double.parseDouble(range[0]));
                    ps.setDouble(index++, Double.parseDouble(range[1]));
                } else if (range.length == 1) {
                    ps.setDouble(index++, Double.parseDouble(range[0]));
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int totalProducts = rs.getInt(1);
                    totalPages = (int) Math.ceil((double) totalProducts / 12); // 12 sản phẩm mỗi trang
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPages;
    }
}