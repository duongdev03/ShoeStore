<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Chi tiết đơn hàng</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./css/styles.css" rel="stylesheet" />
        <link href="./css/listproducts.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            .container{
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 50px;
            }

            .invoice {
                width: 500px;
                padding: 20px;
                border: 1px solid #000;
                border-radius: 10px;
                background-color: #f9f9f9;
            }

            .oder_id {
                text-align: center;
                padding-bottom: 15px;
            }

            .oder_id h3 {
                line-height: 50px;
            }

            .customer,
            .address,
            .phoneNumber,
            .product,
            .OrderDate,
            .PaymentMethod,
            .TotalAmount {
                padding-left: 20px;
                margin-bottom: 20px;
            }

            .order-infor {
                display: grid;
                grid-template-columns: 1fr 2.5fr;
                gap: 30px 5px;
                grid-template-areas:
                    "tittle"
                    "content";
            }

            .order-infor .tittle {
                font-weight: bold;
            }

            .order-infor .content {
                color: #5d5d5d;
            }

            .product ul {
                list-style: none;
                padding-left: 20px;
            }

            .product li {
                margin-bottom: 5px;
            }

            .order-infor hr {
                border-top: 1px solid #ddd;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="menu.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <div class="container">
                <c:if test="${not empty orderDetails}">   
                    <div class="invoice">
                        <c:forEach var="detail" items="${orderDetails}">
                            <!-- Mã đơn -->
                            <div class="oder_id">
                                <h3>Mã đơn: <span>${detail.orderId}</span></h3>
                            </div>
                            <hr>

                            <!-- Tên khách hàng -->
                            <div class="customer">
                                <h4>Tên khách hàng: <span>${detail.customerName}</span></h4>                           
                            </div>
                            <hr>

                            <!-- Địa chỉ -->
                            <div class="address">
                                <h4>Địa chỉ: <span>${detail.address}</span></h4>                           
                            </div>
                            <hr>

                            <!-- Số điện thoại -->
                            <div class="phoneNumber">
                                <h4>Số điện thoại: <span>${detail.phoneNumber}</span></h4>                           
                            </div>
                            <hr>

                            <!-- Chi tiết sản phẩm -->
                            <div class="product order-infor">
                                <div class="tittle">Sản phẩm:</div>
                                <div class="content">
                                    ${detail.productDetails}
                                </div>          
                            </div>

                            <!-- Ngày đặt -->
                            <div class="OrderDate order-infor">
                                <div class="tittle">Ngày đặt:</div>
                                <div class="content">${detail.orderDate}</div>          
                            </div>

                            <!-- Phương thức thanh toán -->
                            <div class="PaymentMethod order-infor">
                                <div class="tittle">Phương thức thanh toán:</div>
                                <div class="content">${detail.paymentMethod}</div>          
                            </div>

                            <!-- Tổng thu -->
                            <div class="TotalAmount order-infor">
                                <div class="tittle">Tổng thu:</div>
                                <div class="content">${detail.totalAmount}</div>          
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty orderDetails}">
                    <p class="no-order-details">Không tìm thấy chi tiết đơn hàng.</p>
                </c:if>
            </div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
