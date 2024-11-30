package controll;

import dao.ProductsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.tomcat.jakartaee.commons.io.FilenameUtils;

@WebServlet(name = "addProductControll", urlPatterns = {"/addProduct"})
@MultipartConfig
public class addProductControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pName = request.getParameter("product_name");
        String pDescription = request.getParameter("description");
        String pPrice = request.getParameter("price");
        String pCategory_id = request.getParameter("category_id");
        
         // Xử lý tệp tải lên
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Tên tệp
        String fileExtension = FilenameUtils.getExtension(fileName);
        String newFileName = System.currentTimeMillis() + "." + fileExtension; // Đổi tên tệp để tránh trùng lặp
        String uploadPath = getServletContext().getRealPath("") + File.separator + "shoesImg";
        
        // Lưu tệp ảnh vào thư mục
        filePart.write(uploadPath + File.separator + newFileName);
        
        ProductsDAO dao = new ProductsDAO();
        dao.addProduct(pName, pDescription, pPrice, newFileName, pCategory_id);
        response.sendRedirect("listProducts");
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
