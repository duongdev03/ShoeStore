<%@page import="connect.DBConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.sql.*, java.io.*, java.util.*, java.time.LocalDateTime" %>
<%@ page import="entity.Customers, entity.Orders, entity.payments" %>

<%
    // Retrieve form data
    String productName = request.getParameter("product_name");
    String size = request.getParameter("size");
    double price = Double.parseDouble(request.getParameter("price"));
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    double subtotal = Double.parseDouble(request.getParameter("subtotal"));
    String customerName = request.getParameter("customerName");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String paymentMethod = request.getParameter("payment_method");

    // Establish database connection
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Your connection logic
        conn = new DBConnect().getConnection();

        // Insert into customers table
        String insertCustomerQuery = "INSERT INTO customers (user_id, customer_name, phone_number, address) VALUES (?, ?, ?, ?)";
        ps = conn.prepareStatement(insertCustomerQuery, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, ((entity.Users)session.getAttribute("acc")).getUser_id());
        ps.setString(2, customerName);
        ps.setString(3, phone);
        ps.setString(4, address);
        ps.executeUpdate();

        // Retrieve the generated customer_id
        int customer_id = -1;
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            customer_id = rs.getInt(1);
        }

        // Insert into orders table
        String insertOrderQuery = "INSERT INTO orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
        LocalDateTime orderDate = LocalDateTime.now(); // Assuming current date/time
        ps = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, customer_id);
        ps.setObject(2, orderDate);
        ps.setDouble(3, subtotal); // Use subtotal or calculate total_amount as needed
        ps.setString(4, "Chờ xử lý"); // Initial status
        ps.executeUpdate();

        // Retrieve the generated order_id
        int order_id = -1;
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            order_id = rs.getInt(1);
        }
        
        // Insert into payments table
        String insertPaymentQuery = "INSERT INTO payments (order_id, payment_date, amount, method) VALUES (?, ?, ?, ?)";
        LocalDateTime paymentDate = LocalDateTime.now();
        ps = conn.prepareStatement(insertPaymentQuery);
        ps.setInt(1, order_id);
        ps.setObject(2, paymentDate);
        ps.setDouble(3, subtotal);
        ps.setString(4, paymentMethod);
        ps.executeUpdate();

        // Retrieve product details for OrderDetails
        String[] productSizeIds = request.getParameterValues("product_size_id");
        String[] quantities = request.getParameterValues("quantity");
        String[] unitPrices = request.getParameterValues("price");

        // Insert into OrderDetails table for each product
        if (productSizeIds != null && quantities != null && unitPrices != null) {
            for (int i = 0; i < productSizeIds.length; i++) {
                int productSizeId = Integer.parseInt(productSizeIds[i]);
                int productQuantity = Integer.parseInt(quantities[i]);
                double productUnitPrice = Double.parseDouble(unitPrices[i]);

                // Insert into OrderDetails table
                String insertOrderDetailsQuery = "INSERT INTO OrderDetails (order_id, product_size_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
                ps = conn.prepareStatement(insertOrderDetailsQuery);
                ps.setInt(1, order_id);  // Use order_id from the inserted order
                ps.setInt(2, productSizeId);  // Use product_size_id from form
                ps.setInt(3, productQuantity);  // Use quantity from form
                ps.setDouble(4, productUnitPrice);  // Use price from form
                ps.executeUpdate();
                
                // Update ProductSizes table to reduce the stock quantity
                String updateStockQuery = "UPDATE ProductSizes SET stock_quantity = stock_quantity - ? WHERE product_size_id = ?";
                ps = conn.prepareStatement(updateStockQuery);
                ps.setInt(1, productQuantity);  // Subtract quantity ordered from stock
                ps.setInt(2, productSizeId);  // Identify product by product_size_id
                ps.executeUpdate();
            }
        }

        // Display order confirmation
        out.println("<h2>Order Confirmation</h2>");
        out.println("<p>Thank you, " + customerName + ", for your order!</p>");
        
        out.println("<p>Order Date: " + orderDate + "</p>");
        out.println("<p>Total Amount: $" + subtotal + "</p>");
        out.println("Product Size IDs: " + Arrays.toString(productSizeIds));

    } catch (SQLException e) {
        // Handle exceptions
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
