<%
    if (session.getAttribute("acc") == null) {
        response.sendRedirect("login");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Cart</title>
    </head>
    <body>
        <section id="header">
            <a href="#"><img src="logo/LogoStore.png" class="logo" alt=""></a>
            <div id="search-bar">
                <form action="search" method="get" class="d-flex">
                    <input type="text" name="query" class="form-control" placeholder="Tìm kiếm sản phẩm..."
                        required>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-search"></i>
                    </button>
                </form>        
            </div>
            <div>
                <ul id="navbar">
                    <li><a href="shop">Trang chủ</a></li>
                        <c:if test="${sessionScope.acc != null}">
                        <li><a href="personal">${sessionScope.acc.username}</a></li>
                        <li><a href="logout">Đăng xuất</a></li>
                        </c:if>
                        <c:if test="${sessionScope.acc == null}">
                        <li><a href="login">Đăng nhập</a></li>
                        </c:if>
                    <li id="lg-bag"><a href="cart"><i class="bi bi-cart4"></i></a></li>
                </ul>
            </div>
        </section>

        <section id="cart" class="section-p1">
            <table width="100%">
                <thead>
                    <tr>
                        <td>xóa</td>
                        <td>ảnh</td>
                        <td>Sản phẩm</td>
                        <td>Size</td>
                        <td>Giá tiền</td>
                        <td>Số lượng</td>
                        <td>Tổng tiền</td>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty param.product_name}">
                        <tr>
                            <td><a href="#"><i class="bi bi-x-circle"></i></a></td>
                            <td><img src="shoesImg/${param.image_url}" alt=""></td>
                            <td>${param.product_name}</td>
                            <td>${param.size}</td>
                            <td><fmt:formatNumber type="currency" currencyCode="VND" value="${param.price}" /></td>
                            <td><input type="number" value="${param.quantity}"></td>
                            <td><fmt:formatNumber type="currency" currencyCode="VND" value="${param.price * param.quantity}" /></td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </section>

        <section id="cart-add" class="section-p1">
            <div id="coupon">
                <h3>Áp dụng phiếu giảm giá</h3>
                <div>
                    <input type="text" placeholder="Nhập mã giảm giá của bạn">
                    <button class="normal">Áp dụng</button>
                </div>
            </div>

            <div id="subtotal">
                <h3>Tổng tiền</h3>
                <table>
                    <tr>
                        <td>Tổng tiền đơn hàng</td>
                        <td><fmt:formatNumber type="currency" currencyCode="VND" value="${param.price * param.quantity}" /></td>
                    </tr>
                    <tr>
                        <td>Vận chuyển</td>
                        <td>Miễn phí</td>
                    </tr>
                    <tr>
                        <td><strong>Phải thanh toán</strong></td>
                        <td><strong><fmt:formatNumber type="currency" currencyCode="VND" value="${param.price * param.quantity}" /></strong></td>
                    </tr>
                </table>

                <form action="orders.jsp" method="post">
                    <input type="hidden" name="product_name" value="${param.product_name}">
                    <input type="hidden" name="image_url" value="${param.image_url}">
                    <input type="hidden" name="size" value="${param.size}">
                    <input type="hidden" name="price" value="${param.price}">
                    <input type="hidden" name="quantity" value="${param.quantity}">
                    <input type="hidden" name="subtotal" value="${param.price * param.quantity}">
                    <input type="hidden" name="product_size_id" value="${param.product_size_id}">
                    <button type="submit" class="normal btn-proceed">Thanh toán</button>
                </form>

            </div>
        </section>

        <section id="newsletter" class="section-p1 section-m1">
            <div class="newtext">
                <h4>Đăng ký nhận bản tin</h4>
                <p>Nhận email cập nhật mới nhất về cửa hàng và <span>các ưu đãi đặc biệt của chúng tôi.</span> </p>
            </div>
            <div class="form">
                <input type="text" placeholder="Địa chỉ Email của bạn">
                <button class="normal">Đăng ký</button>
            </div>
        </section>


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
