
package controll;

import dao.ProductsizesDAO;
import entity.Productsizes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateWarehouseControll", urlPatterns = {"/updateWarehouse"})
public class updateWarehouseControll extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PsID = request.getParameter("product_size_id");
        ProductsizesDAO dao = new ProductsizesDAO();
        Productsizes ps = dao.getProductsizeByID(PsID);
        
        request.setAttribute("productsize", ps);
        request.getRequestDispatcher("updateStockQuantity.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PsID = request.getParameter("product_size_id");
        int stock_quantity = Integer.parseInt(request.getParameter("stock_quantity"));
        
        ProductsizesDAO dao = new ProductsizesDAO();
        dao.updateStockQuantity(PsID, stock_quantity);
        response.sendRedirect("listStockQuantity");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
