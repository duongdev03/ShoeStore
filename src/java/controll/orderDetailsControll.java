package controll;

import dao.OrderDetailsDAO;
import entity.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "orderDetailsControll", urlPatterns = {"/orderDetail"})
public class orderDetailsControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Lấy order_id từ request (được truyền từ giao diện)
        String orderIdParam = request.getParameter("order_id");
        
        if (orderIdParam != null) {
            try {
                int orderId = Integer.parseInt(orderIdParam);
                OrderDetailsDAO dao = new OrderDetailsDAO();
                
                // Lấy danh sách chi tiết đơn hàng từ DAO
                List<OrderDetail> orderDetails = dao.getOrderDetailsByOrderId(orderId);
                
                // Đưa dữ liệu chi tiết đơn hàng vào request để truyền sang JSP
                request.setAttribute("orderDetails", orderDetails);
                
                // Chuyển hướng sang JSP để hiển thị kết quả
                request.getRequestDispatcher("/orderDetail.jsp").forward(request, response);
                
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu order_id không hợp lệ
                request.setAttribute("error", "Mã đơn hàng không hợp lệ!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (Exception e) {
                // Xử lý lỗi khác
                request.setAttribute("error", "Đã xảy ra lỗi khi truy vấn dữ liệu!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // Nếu không có order_id trong request
            request.setAttribute("error", "Vui lòng cung cấp mã đơn hàng!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để xử lý chi tiết đơn hàng";
    }
}
