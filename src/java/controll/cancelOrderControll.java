
package controll;

import dao.OrdersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "cancelOrderControll", urlPatterns = {"/cancelOrder"})
public class cancelOrderControll extends HttpServlet {

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
        String orderId = request.getParameter("orderId");
        String status = "Hủy";
        

        OrdersDAO orderDAO = new OrdersDAO();
        orderDAO.updateOrder(orderId, status);
        response.sendRedirect("personal");
        System.out.println("Order ID: " + orderId);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
