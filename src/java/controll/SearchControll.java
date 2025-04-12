package controll;

import dao.SearchProductDAO;
import dao.CategoriesDAO;
import entity.Products;
import entity.Categories;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SearchControll", urlPatterns = {"/search"})
public class SearchControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String sort = request.getParameter("sort");
        int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));

        SearchProductDAO searchProductDAO = new SearchProductDAO();
        List<Products> products = searchProductDAO.searchProducts(query, category, price, sort, page);
        int totalPages = searchProductDAO.getTotalPages(query, category, price);

        CategoriesDAO categoriesDAO = new CategoriesDAO();
        List<Categories> categories = categoriesDAO.getAllCategories();

        request.setAttribute("products", products);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("search.jsp").forward(request, response);
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
