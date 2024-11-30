<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Sửa thông tin hàng hóa</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./css/styles.css" rel="stylesheet" />
        <link href="./css/listproducts.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="menu.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <div class="container">
                    <h1 style="text-align: center;">Sửa Thông Tin Sản Phẩm</h1>
                    <form action="updateProduct" method="post" enctype="multipart/form-data">   
                        <div class="mb-3 mt-3">
                            <label for="Product_id" class="modal-lable">ID:</label>
                            <input value="${product.product_id}" type="text" class="modal-input"  name="product_id" readonly>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="Product_name" class="modal-lable">Tên sản phẩm:</label>
                        <input value="${product.product_name}" type="text" class="modal-input" name="product_name" required>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="description" class="modal-lable">Mô tả:</label>
                        <input value="${product.description}" type="text" class="modal-input" name="description" required>
                    </div>

                    <div class="mb-3">
                        <label for="DOB" class="modal-lable">Giá tiền:</label>
                        <input value="${product.price}" type="text" class="modal-input" name="price" required>
                    </div>
                    <div class="mb-3">
                        <img src="./shoesImg/${product.image_url}" width="100px" height="100px" alt="ảnh sản phẩm"/>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="image" class="modal-lable">Ảnh sản phẩm:</label>
                        <input type="file" class="modal-input" name="image">
                    </div>

                    <label for="category_id" class="modal-lable">Danh mục hàng:</label>
                    <select class="modal-input" name="category_id" required>
                        <c:forEach items="${listC}" var="category">
                            <option value="${category.category_id}" ${category.category_id == product.category_id ? 'selected' : ''}>
                                ${category.category_name}
                            </option>
                        </c:forEach>
                    </select><br>
                    <button type="submit" class="btn-add-doctor">Sửa Thông Tin</button>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>


