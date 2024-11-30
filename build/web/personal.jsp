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
        <title>Shop</title>
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
        
        <div class="container">
            <div class="title">
                Đơn hàng của bạn
            </div>
            
            <div class="content-box">
                <div class="product-infor">
                    <div class="product-img">
                        <<img src="src" alt="alt"/>
                    </div>
                    
                    <div class="product-content">
                        <p>tên sản phẩm</p>
                        <p>size: 40</p>
                        <p>số lượng: 1</p>
                    </div>
                </div>
                
                <div class="invoice-status">
                    
                </div>
            </div>
        </div>


        <script src="main.js"></script>
    </body>
</html>

