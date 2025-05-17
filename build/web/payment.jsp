<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <fmt:formatNumber value="${sessionScope.subtotal}" type="number" var="amountRaw" maxFractionDigits="0"
                groupingUsed="false" />
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Thanh toán chuyển khoản</title>
                <style>
                    #QRCode {
                        display: flex;
                        justify-content: center;
                        align-items: center;
                    }
                    .check-status {
                        display: block;
                        font-size: 16px;
                        margin: 20px auto;
                        padding: 10px 20px;
                        background-color: #4CAF50;
                        color: white;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                    }
                    .check-status:hover {
                        background-color: #45a049;
                    }
                </style>
            </head>

            <body>
                <h1>Thanh toán chuyển khoản</h1>
                <p>Vui lòng quét mã QR để thanh toán số tiền:
                    <strong>
                        <fmt:formatNumber type="currency" currencyCode="VND" value="${sessionScope.subtotal}" />
                    </strong>
                    cho đơn hàng của bạn.
                </p>
                <div id="QRCode">
                    <img src="https://img.vietqr.io/image/${BANK_ID}-${ACCOUNT_NUMBER}-compact2.png?amount=${amountRaw}&addInfo=Thanh%20toan%20don%20hang%20${orderId}"
                        alt="QR Code" width="400" height="500" />
                </div>
                <form action="checkPayment" method="post">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <input type="hidden" name="subtotal" value="${subtotal}">
                    <button type="submit" class="check-status">Kiểm tra trạng thái thanh toán</button>
                </form>

            </body>

            </html>