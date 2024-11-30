package controll;

import dao.ReportDAO;
import entity.MonthlyRevenue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Duong
 */
@WebServlet(name = "reportControll", urlPatterns = {"/report"})
public class reportControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Lấy doanh thu của tháng hiện tại từ ReportDAO
        ReportDAO reportDAO = new ReportDAO();
        double revenue = reportDAO.getRevenueCurrentMonth();

        // Format số tiền thành định dạng tiền tệ
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = DecimalFormat.getCurrencyInstance(locale);
        String formattedRevenue = currencyFormatter.format(revenue);

        // Lấy tên sản phẩm bán chạy nhất
        String bestSellingProduct = reportDAO.getBestSellingProduct();
        int cancelledOrdersCount = reportDAO.getCancelledOrdersCount();
        
        ReportDAO dao = new ReportDAO();
        List<MonthlyRevenue> monthlyRevenueList = dao.getMonthlyRevenue();

        request.setAttribute("monthlyRevenueList", monthlyRevenueList);
        request.setAttribute("revenue", formattedRevenue);
        request.setAttribute("bestSellingProduct", bestSellingProduct);
        request.setAttribute("cancelledOrdersCount", cancelledOrdersCount);

        request.getRequestDispatcher("report.jsp").forward(request, response);

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
