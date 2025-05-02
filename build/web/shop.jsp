<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                    crossorigin="anonymous">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                    crossorigin="anonymous"></script>
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
                <link rel="stylesheet" href="./css/homepage.css">
                <title>Shop</title>
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

                <section id="page-header">
                    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                        <!-- Indicators (dấu chấm chuyển ảnh) -->
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                                class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                                aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                                aria-label="Slide 3"></button>
                        </div>

                        <!-- Các ảnh trong slider -->
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="image/slider/slider6.png" class="d-block w-100" alt="Banner 1">
                            </div>
                            <div class="carousel-item">
                                <img src="image/slider/slider5.png" class="d-block w-100" alt="Banner 2">
                            </div>
                            <div class="carousel-item">
                                <img src="image/slider/slider4.png" class="d-block w-100" alt="Banner 3">
                            </div>
                        </div>

                        <!-- Nút điều hướng -->
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </section>

                <section id="product1" class="section-p1">
                    <div class="pro-container">
                        <c:forEach items="${listP}" var="p">
                            <div class="pro" onclick="window.location.href = 'detail?pid=${p.product_id}';">

                                <img src="./shoesImg/${p.image_url}" alt="">
                                <div class="des">
                                    <span>${p.category_id}</span>
                                    <h5>${p.product_name}</h5>
                                    <div class="star">
                                        <i class="bi bi-star-fill"></i>
                                        <i class="bi bi-star-fill"></i>
                                        <i class="bi bi-star-fill"></i>
                                        <i class="bi bi-star-fill"></i>
                                        <i class="bi bi-star-fill"></i>
                                    </div>
                                    <h4>
                                        <fmt:formatNumber type="currency" currencyCode="VND" value="${p.price}" />
                                    </h4>
                                </div>
                                <a href="#"><i class="bi bi-cart4 cart"></i></a>
                            </div>
                        </c:forEach>
                    </div>
                </section>

                <!--        <section id="pagination" class="section-p1">
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#"><i class="bi bi-arrow-right"></i></a>
        </section>-->


                <section id="newsletter" class="section-p1 section-m1">
                    <div class="newtext">
                        <h4>Đăng ký nhận bản tin</h4>
                        <p>Nhận email cập nhật mới nhất về cửa hàng và <span>các ưu đãi đặc biệt của chúng tôi.</span>
                        </p>
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


                <script src="js/main.js"></script>
            </body>

            </html>