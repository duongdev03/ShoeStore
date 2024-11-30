package controll;

import dao.CategoriesDAO;
import entity.Categories;
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
@WebServlet(name = "updateCategoryControll", urlPatterns = {"/updateCategory"})
public class updateCategoryControll extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Cid = request.getParameter("category_id");
        CategoriesDAO dao = new CategoriesDAO();
        Categories cate = dao.getCategoryByID(Cid);
        request.setAttribute("category", cate);
        request.getRequestDispatcher("updateCategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Cid = request.getParameter("category_id");
        String Cname = request.getParameter("category_name");
        
        CategoriesDAO dao = new CategoriesDAO();
        dao.updateCategory(Cid, Cname);
        response.sendRedirect("listCategories");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
