<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="./css/homepage.css">
        <link rel="stylesheet" href="./css/personal.css">
        <title>Shop</title>
    </head>
    <body>

        <section id="header">
            <a href="#"><img src="logo/LogoStore.png" class="logo" alt=""></a>

            <div>
                <ul id="navbar">
                    <li><a href="shop">Trang chủ</a></li>
                        <c:if test="${sessionScope.acc != null}">
                        <li><a href="#">${sessionScope.acc.username}</a></li>
                        <li><a href="logout">Đăng xuất</a></li>
                        </c:if>
                        <c:if test="${sessionScope.acc == null}">
                        <li><a href="login">Đăng nhập</a></li>
                        </c:if>
                    <li id="lg-bag"><a href="cart"><i class="bi bi-cart4"></i></a></li>
                </ul>
            </div>
        </section>

        <div class="container">

            <div class="content-box">
                <div class="title">
                    Đơn hàng của bạn
                </div>
                <c:forEach var="order" items="${userOrders}">
                    <div class="product-infor">
                        <div class="product-img">
                            <img src="./shoesImg/${order.image_url}" alt="Ảnh sản phẩm" />
                        </div>
            
                        <div class="product-content">
                            <p>${order.productName}</p>
                            <p>size: ${order.size}, số lượng: ${order.quantity}</p>
            
                            <div class="invoice-status">
                                <div class="status">
                                    <span style="opacity: 0.7;">Trạng thái: </span>${order.status}
                                </div>
            
                                <c:if test="${order.status eq 'Hủy' || order.status eq 'Đã giao'}">
                                    <form action="reOrder" method="post">
                                        <input type="hidden" name="orderId" value="${order.orderId}" />
                                        <button type="submit" class="re-order">Mua lại</button>
                                    </form>
                                </c:if>
                                <c:if test="${order.status ne 'Hủy' && order.status ne 'Đã giao'}">
                                    <form action="cancelOrder" method="post">
                                        <input type="hidden" name="orderId" value="${order.orderId}" />
                                        <button type="submit" class="cancel-order">Hủy</button>
                                    </form>
                                </c:if>
                                
                            </div>
                        </div>
                    </div>
                </c:forEach>
            
                <c:if test="${empty userOrders}">
                    <p>Bạn chưa có đơn hàng nào.</p>
                </c:if>
            </div>
        </div>

        <footer class="section-p1">
            <div class="col">
                <img src="logo/LogoStore.png" class="logo" alt="">
                <h4>Liên hệ</h4>
                <p><strong>địa chỉ: </strong> Trinh Van Bo Street, Nam Tu Liem District, Hanoi City</p>
                <p><strong>điện thoại: </strong> 024 3555 2008</p>
                <p><strong>giờ làm việc: </strong> 7:00 - 21:00, thứ 2 - chủ nhật</p>
                <div class="follow">
                    <h4>Theo dõi chúng tôi</h4>
                    <div class="icon">
                        <i class="bi bi-facebook"></i>
                        <i class="bi bi-messenger"></i>
                        <i class="bi bi-instagram"></i>
                        <i class="bi bi-tiktok"></i>
                        <i class="bi bi-youtube"></i>
                    </div>
                </div>
            </div>

            <div class="col">
                <h4>điều khoản</h4>
                <a href="#">Về chúng tôi</a>
                <a href="#">Thông tin giao hàng</a>
                <a href="#">Chính sách bảo mật</a>
                <a href="#">Điều khoản & Điều kiện</a>
                <a href="#">Liên hệ với chúng tôi</a>
            </div>

            <div class="col">
                <h4>Tài khoản của tôi</h4>
                <a href="#">Đăng nhập</a>
                <a href="#">Xem giỏ hàng</a>
                <a href="#">Danh sách mong muốn của tôi</a>
                <a href="#">Theo dõi đơn hàng của tôi</a>
                <a href="#">Trợ giúp</a>
            </div>

            <div class="col install">
                <h4>Cài đặt ứng dụng</h4>
                <p>Từ App Store hoặc Google Play</p>
                <div class="row">
                    <img src="pay/app.png" alt="">
                    <img src="pay/ggplay.png" alt="">
                </div>
            </div>

        </footer>

        <script src="./js/main.js"></script>
    </body>
</html>

