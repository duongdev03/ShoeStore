
package controll;

import dao.OrderDetailsDAO;
import entity.UserOrder;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "userOdersControll", urlPatterns = {"/personal"})
public class userOdersControll extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Users acc = (Users) session.getAttribute("acc");
        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = acc.getUser_id();
        
        try {
            OrderDetailsDAO dao = new OrderDetailsDAO();
            List<UserOrder> userOrders = dao.getUserOrders(userId);

            // Gửi dữ liệu sang JSP
            request.setAttribute("userOrders", userOrders);
            request.getRequestDispatcher("personal.jsp").forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi tải đơn hàng.");
            request.getRequestDispatcher("personal.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
