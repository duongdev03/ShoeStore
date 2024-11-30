package controll;

import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Duong
 */
@WebServlet(name = "addAccountControll", urlPatterns = {"/addAccount"})
public class addAccountControll extends HttpServlet {

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
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        
        UsersDAO dao = new UsersDAO();
        dao.addAccount(username, pass, email, role);
        response.sendRedirect("listAccountAdmin");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
