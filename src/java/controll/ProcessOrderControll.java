package controll;

import dao.ProcessOrderDAO;
import entity.Customers;
import entity.Orders;
import entity.Users;
import entity.orderdetails;
import entity.payments;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;



@WebServlet(name = "ProcessOrderControll", urlPatterns = {"/processOrder"})
public class ProcessOrderControll extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        try{
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("acc");

            // Lấy thông tin khách hàng từ form
            String customerName = request.getParameter("customerName");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String paymentMethod = request.getParameter("payment_method");

            // Lấy thông tin sản phẩm từ form
            String productName = request.getParameter("product_name");
            String imageUrl = request.getParameter("image_url");
            String size = request.getParameter("size");
            int productSizeId = Integer.parseInt(request.getParameter("product_size_id"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double subtotal = Double.parseDouble(request.getParameter("subtotal"));
            
            // Tạo đối tượng khách hàng
            Customers customer = new Customers();
            customer.setUser_id(String.valueOf(user.getUser_id()));
            customer.setCustomer_name(customerName);
            customer.setPhone_number(phone);
            customer.setAddress(address);

            // Tạo đối tượng đơn hàng
            LocalDateTime localDateTime = LocalDateTime.now();
            Date orderDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            Orders order = new Orders();
            order.setOrder_date(orderDate);
            order.setTotal_amount(subtotal);
            order.setStatus("Đang xử lý");

            // Tạo đối tượng thanh toán
            payments payment = new payments();
            payment.setPayment_date(LocalDateTime.now());
            payment.setAmount(subtotal);
            payment.setMethod(paymentMethod);

            // Tạo danh sách chi tiết đơn hàng
            List<orderdetails> details = new ArrayList<>();
            orderdetails detail = new orderdetails();
            detail.setProduct_size_id(productSizeId);
            detail.setQuantity(quantity);
            detail.setUnit_price(price);
            details.add(detail);

            // Gọi DAO để xử lý việc tạo đơn hàng
            ProcessOrderDAO processOrderDAO = new ProcessOrderDAO();
            int orderId = processOrderDAO.insertFullOrder(customer, order, payment, details);

            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
