
package controll;

import dao.UsersDAO;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "updateAccountControll", urlPatterns = {"/updateAccount"})
public class updateAccountControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String AccID = request.getParameter("user_id");
        UsersDAO dao = new UsersDAO();
        Users acc = dao.getUserByID(AccID);
        
        request.setAttribute("account", acc);
        request.getRequestDispatcher("updateAccAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String AccID = request.getParameter("user_id");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        
        UsersDAO dao = new UsersDAO();
        dao.updateAcccout(AccID, pass, email);
        response.sendRedirect("listAccountAdmin");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
