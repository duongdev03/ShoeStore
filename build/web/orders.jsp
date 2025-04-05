<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đặt hàng</title>
        <link rel="stylesheet" href="./css/orders.css">
    </head>
    <body>
        <div class="container">
            <h2>Đặt Hàng</h2>

            <h3>Thông tin sản phẩm</h3>
            <table>
                <tr>

                    <td>Sản phẩm</td>
                    <td>Size</td>
                    <td>Giá tiền</td>
                    <td>Số lượng</td>
                    <td>Tổng tiền</td>
                </tr>
                <tr>

                    <td>${param.product_name}</td>
                    <td>${param.size}</td>
                    <td><fmt:formatNumber type="currency" currencyCode="VND" value="${param.price}" /></td>
                    <td>${param.quantity}</td>
                    <td><fmt:formatNumber type="currency" currencyCode="VND" value="${param.subtotal}" /></td>
                </tr>
            </table>

            <h3>Thông tin khách hàng</h3>
            <form id="orderForm" action="processOrder.jsp" method="post">
                <input type="hidden" name="product_name" value="${param.product_name}">
                <input type="hidden" name="image_url" value="${param.image_url}">
                <input type="hidden" name="size" value="${param.size}">
                <input type="hidden" name="product_size_id" value="${param.product_size_id}">
                <input type="hidden" name="price" value="${param.price}">
                <input type="hidden" name="quantity" value="${param.quantity}">
                <input type="hidden" name="subtotal" value="${param.subtotal}">

                <label for="customerName">Tên khách hàng:</label>
                <input type="text" id="customerName" name="customerName" required><br><br>

                <label for="phone">Số điện thoại:</label>
                <input type="tel" id="phone" name="phone" required pattern="[0-9]{10}"><br><br>

                <label for="address">Địa chỉ:</label>
                <textarea id="address" name="address" rows="4" cols="50" required></textarea><br><br>
                <label for="address">Chọn phương thức thanh toán:</label>
                <select name="payment_method">
                    <option value="Tiền mặt">Tiền mặt</option>
                    <option value="Chuyển khoản">Chuyển khoản</option>
                </select>

                <button type="submit" id="submitBtn">Đặt hàng</button>
            </form>
            <p id="confirmation"></p>
        </div>

        <script src="main.js"></script>
    </body>
</html>
