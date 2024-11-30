
package controll;

import dao.ProductsDAO;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginControll", urlPatterns = {"/login"})
public class LoginControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ProductsDAO dao = new ProductsDAO();
        Users a = dao.login(username, password);
        if (a == null) {
            request.setAttribute("mess", "Thông tin tài khoản mật khẩu không chính xác");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            if("admin".equals(a.getRole())){
                response.sendRedirect("dashboad");
            }else{
                response.sendRedirect("shop");
            }     
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
        return "Short description";
    }// </editor-fold>

}
