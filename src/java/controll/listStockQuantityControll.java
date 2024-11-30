
package controll;

import dao.ProductsDAO;
import dao.ProductsizesDAO;
import entity.Products;
import entity.Productsizes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "listStockQuantityControll", urlPatterns = {"/listStockQuantity"})
public class listStockQuantityControll extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductsizesDAO dao = new ProductsizesDAO();
        List<Productsizes> list = dao.getAllProductsizes();
        
        ProductsDAO prodDAO = new ProductsDAO();
        List<Products> listProd = prodDAO.getAllProducts();
        
        request.setAttribute("listPS", list);
        request.setAttribute("listProd", listProd);
        request.getRequestDispatcher("listStockQuantity.jsp").forward(request, response);
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
