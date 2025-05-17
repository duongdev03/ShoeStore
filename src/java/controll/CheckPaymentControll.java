package controll;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.ProcessOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "CheckPaymentControll", urlPatterns = {"/checkPayment"})
public class CheckPaymentControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String orderId = request.getParameter("orderId");
        double subtotal = Double.parseDouble(request.getParameter("subtotal"));

        try {
            String apiUrl = "https://script.googleusercontent.com/macros/echo?user_content_key=AehSKLg7skKVMfqMvwmZo3A6MSA4GM1_9VOFHQRalxFdHtB6bzRLwrfBt7MsVWN19vGRvFoM4Zc2zOwWqhX8cCSHPjzyp0jH0p5qylRnHGw0kHXdy4yqS5r_hKtzm-91tyeB9gzauNMacLCey6uokBQPKdU3Cz_XR5raT4E_kZtvIy-qNY8zVnRY92i_UjFLATBEb59hDEqifD10Ef3Fy8OMVUK5zN8V-kSeiTMw75wznfVi6bvGydcwSfs3MJOnVMVvCafJWi_0on8uO0kQdytPfCG31CI5T4oaS8xPH9ON&lib=M-rhriyYSnocd51AR1YkXKsbJknALTpkX";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line);
            }
            conn.disconnect();

            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(responseBuilder.toString(), JsonObject.class);
            JsonArray transactions = jsonResponse.getAsJsonArray("data");

            boolean paymentMatched = false;

            for (JsonElement transactionElement : transactions) {
                JsonObject transaction = transactionElement.getAsJsonObject();
                String description = transaction.get("Mô tả").getAsString();
                double amount = transaction.get("Giá trị").getAsDouble();

                if (description.toLowerCase().contains(("Thanh toan don hang " + orderId).toLowerCase())) {
                    BigDecimal apiAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal orderAmount = BigDecimal.valueOf(subtotal).setScale(2, RoundingMode.HALF_UP);

                    if (apiAmount.compareTo(orderAmount) == 0) {
                        paymentMatched = true;
                        break;
                    }
                }
            }

            if (paymentMatched) {
                ProcessOrderDAO dao = new ProcessOrderDAO();
                dao.updateOrderStatus(Integer.parseInt(orderId), "Đã thanh toán");
                request.getSession().setAttribute("message", "Thanh toán thành công!");
                response.sendRedirect("orderSuccess.jsp");
            } else {
                request.getSession().setAttribute("message", "Không tìm thấy giao dịch phù hợp. Vui lòng thử lại.");
                response.sendRedirect("payment?orderId=" + orderId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Có lỗi xảy ra trong quá trình thanh toán!");
            response.sendRedirect("payment?orderId=" + orderId);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
