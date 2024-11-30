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
        <title>Product Detail</title>
    </head>
    <body>
        <section id="header">
            <a href="#"><img src="logo/logo.png" class="logo" alt=""></a>

            <div>
                <ul id="navbar">
                    <li><a href="homepage.jsp">Home</a></li>
                    <li><a class="active" href="shop">Shop</a></li>
                        <c:if test="${sessionScope.acc != null}">
                        <li><a href="#">Hello ${sessionScope.acc.username}</a></li>
                        <li><a href="logout">Logout</a></li>
                        </c:if>
                        <c:if test="${sessionScope.acc == null}">
                        <li><a href="login">Login</a></li>
                        </c:if>
                    <li id="lg-bag"><a href="cart"><i class="bi bi-cart4"></i></a></li>
                    <a href="#" id="close"><i class="bi bi-x-lg"></i></a>
                </ul>
            </div>
            <div id="mobile">
                <a href="cart"><i class="bi bi-cart4"></i></a>
                <i id="bar" class="class=bi bi-list"></i>
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
                <h6>Home / Shoes</h6>
                <h4>${detail.product_name}</h4>
                <h2><fmt:formatNumber type="currency" currencyCode="VND" value="${detail.price}" /></h2>
                <form action="cart.jsp" method="post">
                    <select name="size" id="sizeSelect" onchange="updateSizeId()">
                        <option>chọn size</option>
                        <c:forEach var="size" items="${sizes}">
                            <c:choose>
                                <c:when test="${size.stock_quantity > 0}">
                                    <option value="${size.size}" data-size-id="${size.product_size_id}">${size.size}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${size.size}" disabled>${size.size} (Out of Stock)</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>

                    <input type="hidden" name="product_size_id" id="product_size_id">

                    <select name="payment_method">
                        <option>chọn phương thức thanh toán</option>
                        <option value="Tiền mặt">Tiền mặt</option>
                        <option value="Chuyển khoản">Chuyển khoản</option>
                    </select>
                    <input type="hidden" name="product_name" value="${detail.product_name}">
                    <input type="hidden" name="price" value="${detail.price}">
                    <input type="hidden" name="image_url" value="${detail.image_url}">
                    <input type="number" name="quantity" value="1">
                    <button type="submit" class="normal">Add To Cart</button>
                </form>

                <h4>Product Details</h4>
                <span>${detail.description}</span>
            </div>
        </section>

        <!-- Include footer and scripts here -->
        <section id="newsletter" class="section-p1 section-m1">
            <div class="newtext">
                <h4>Sign Up For Newsletters</h4>
                <p>Get E-mail updates about our latest shop and <span>special offers.</span> </p>
            </div>
            <div class="form">
                <input type="text" placeholder="Your email address">
                <button class="normal">Sign Up</button>
            </div>
        </section>
        <!-- Include footer and scripts here -->
        <footer class="section-p1">
            <div class="col">
                <img class="logo" src="logo/logo.png" alt="">
                <h4>Contact</h4>
                <p><strong>Address: </strong> Trinh Van Bo Street, Nam Tu Liem District, Hanoi City</p>
                <p><strong>Phone: </strong> 024 3555 2008</p>
                <p><strong>Hours: </strong> 7:00 - 18:00, Mon - Sat</p>
                <div class="follow">
                    <h4>Follow us</h4>
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
                <h4>About</h4>
                <a href="#">About us</a>
                <a href="#">Delivery Information</a>
                <a href="#">Privacy Policy</a>
                <a href="#">Terms & Conditions</a>
                <a href="#">Contact us</a>
            </div>

            <div class="col">
                <h4>My Account</h4>
                <a href="#">Sign In</a>
                <a href="#">View Cart</a>
                <a href="#">My Wishlist</a>
                <a href="#">Track My Order</a>
                <a href="#">Help</a>
            </div>

            <div class="col install">
                <h4>Install App</h4>
                <p>From App Store or Google Play</p>
                <div class="row">
                    <img src="pay/app.png" alt="">
                    <img src="pay/ggplay.png" alt="">
                </div>
                <p>Secured Payment Gateways </p>
                <img src="pay/visa.png" alt="">
            </div>

            <div class="copyright">
                <p>Nhóm 8 - Công Nghệ Java - Quản lý giày dép công ty Nam Dương 2024</p>
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
        </script>
    </body>
</html>
