
package controll;

import dao.CategoriesDAO;
import dao.ProductsDAO;
import entity.Categories;
import entity.Products;
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
import java.util.List;
import org.apache.tomcat.jakartaee.commons.io.FilenameUtils;

@WebServlet(name = "updateProductControll", urlPatterns = {"/updateProduct"})
@MultipartConfig
public class updateProductControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Pid = request.getParameter("product_id");
        ProductsDAO dao = new ProductsDAO();
        Products p = dao.getProductByID(Pid);

        CategoriesDAO categoriesDAO = new CategoriesDAO();
        List<Categories> listCategories = categoriesDAO.getAllCategories();

        request.setAttribute("listC", listCategories);

        request.setAttribute("product", p);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Pid = request.getParameter("product_id");
        String Pname = request.getParameter("product_name");
        String Pdescription = request.getParameter("description");
        String Pprice = request.getParameter("price");
        Part filePart = request.getPart("image"); // Lấy file ảnh
        String categoryId = request.getParameter("category_id");

        ProductsDAO dao = new ProductsDAO();
        Products p = dao.getProductByID(Pid);
        String currentImage = p.getImage_url(); // Lấy tên file ảnh hiện tại

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Tên tệp
        if (filePart.getSize() > 0) {
            // Nếu người dùng đã chọn file ảnh mới
            String fileExtension = FilenameUtils.getExtension(fileName);
            String newFileName = System.currentTimeMillis() + "." + fileExtension; // Đổi tên tệp để tránh trùng lặp
            String uploadPath = getServletContext().getRealPath("") + File.separator + "shoesImg";

            // Lưu tệp ảnh mới vào thư mục
            filePart.write(uploadPath + File.separator + newFileName);

            // Cập nhật thông tin sản phẩm với file ảnh mới
            dao.updateProduct(Pid, Pname, Pdescription, Pprice, newFileName, categoryId);

            // Xóa file ảnh cũ (nếu cần)
            File oldFile = new File(uploadPath + File.separator + currentImage);
            if (oldFile.exists()) {
                oldFile.delete();
            }
        } else {
            // Nếu người dùng không chọn file ảnh mới, giữ lại ảnh cũ
            dao.updateProduct(Pid, Pname, Pdescription, Pprice, currentImage, categoryId);
        }

        response.sendRedirect("listProducts");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
