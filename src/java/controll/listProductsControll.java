package controll;

import dao.CategoriesDAO;
import dao.ProductsDAO;
import entity.Categories;
import entity.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "listProductsControll", urlPatterns = {"/listProducts"})
public class listProductsControll extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductsDAO dao = new ProductsDAO();
        List<Products> list = dao.getAllProducts();
        
        CategoriesDAO categoriesDAO = new CategoriesDAO();
        List<Categories> listCategories = categoriesDAO.getAllCategories();
        
        request.setAttribute("listC", listCategories);
        request.setAttribute("listP", list);
        request.getRequestDispatcher("listProducts.jsp").forward(request, response);
        
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
