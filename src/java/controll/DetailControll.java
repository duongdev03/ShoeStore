package controll;

import dao.ProductsDAO;
import dao.ProductsizesDAO;
import entity.Products;
import entity.Productsizes;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DetailControll", urlPatterns = {"/detail"})
public class DetailControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String product_id = request.getParameter("pid");
        ProductsDAO dao = new ProductsDAO();
        ProductsizesDAO sizesDao = new ProductsizesDAO();
        Products p = null;
        List<Productsizes> sizes = null;
        if (product_id != null) {
            try {
 
                p = dao.getProductByID(product_id);
                sizes = sizesDao.getSizesByProductId(product_id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("detail", p);
        request.setAttribute("sizes", sizes);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
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
    }
}
