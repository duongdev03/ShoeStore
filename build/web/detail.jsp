<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <title>Product Detail</title>
            </head>

            <body>
                <section id="header">
                    <a href="#"><img src="logo/LogoStore.png" class="logo" alt=""></a>

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

                <section id="prodetails" class="section-p1">
                    <div class="single-pro-image">
                        <img src="./shoesImg/${detail.image_url}" width="100%" id="MainImg" alt="">

                        <div class="small-img-group">
                            <!-- If you have small images to show -->
                        </div>
                    </div>

                    <div class="single-pro-details">
                        <h6>Trang chủ / Thông tin sản phẩm</h6>
                        <h4>${detail.product_name}</h4>
                        <h2>
                            <fmt:formatNumber type="currency" currencyCode="VND" value="${detail.price}" />
                        </h2>
                        <c:if test="${sessionScope.acc != null}">
                            <form action="cart.jsp" method="post">
                                <select name="size" id="sizeSelect" onchange="updateSizeId()">
                                    <option value="default">chọn size</option>
                                    <c:forEach var="size" items="${sizes}">
                                        <c:choose>
                                            <c:when test="${size.stock_quantity > 0}">
                                                <option value="${size.size}" data-size-id="${size.product_size_id}">
                                                    ${size.size}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${size.size}" disabled>${size.size} (hết hàng)</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>

                                <input type="hidden" name="product_size_id" id="product_size_id">
                                <input type="hidden" name="product_name" value="${detail.product_name}">
                                <input type="hidden" name="price" value="${detail.price}">
                                <input type="hidden" name="image_url" value="${detail.image_url}">
                                <input type="number" name="quantity" value="1">
                                <button type="submit" class="normal" onclick="return validateSize()">Mua</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.acc == null}">
                            <p>Vui lòng <a href="login">đăng nhập</a> để mua hàng.</p>
                        </c:if>

                        <h4>Mô tả</h4>
                        <span>${detail.description}</span>
                    </div>
                </section>

                <!-- Include footer and scripts here -->
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

                <script src="./js/main.js"></script>
                <script>
                    // Hàm để cập nhật product_size_id khi người dùng chọn size
                    function updateSizeId() {
                        var sizeSelect = document.getElementById('sizeSelect');
                        var selectedOption = sizeSelect.options[sizeSelect.selectedIndex];
                        var productSizeId = selectedOption.getAttribute('data-size-id');
                        document.getElementById('product_size_id').value = productSizeId;
                    }

                    function validateSize() {
                        const sizeSelect = document.getElementById("sizeSelect");
                        if (sizeSelect.value === "default") {
                            alert("Vui lòng chọn size trước khi mua!");
                            return false;
                        }
                        return true;
                    }
                </script>
            </body>

            </html>