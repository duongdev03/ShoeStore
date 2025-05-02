<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Danh sách hàng hóa</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                    crossorigin="anonymous">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                    crossorigin="anonymous"></script>
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="./css/styles.css" rel="stylesheet" />
                <link href="./css/listproducts.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="menu.jsp"></jsp:include>
                <c:if test="${param.message == 'addProductSuccess'}">
                    <script>
                        window.onload = function () {
                            alert("Thêm sản phẩm thành công!");
                        }
                    </script>
                </c:if>

                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <button type="button" class="btn btn-success btn-add js-addBS">
                                <a href="#">
                                    THÊM SẢN PHẨM MỚI
                                </a>
                            </button>
                        </div>
                        <div class="container-fluid px-4">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Danh Sách Sản Phẩm
                                </div>
                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tên sản phẩm</th>
                                                <th>Mô tả</th>
                                                <th>Giá</th>
                                                <th>Ảnh</th>
                                                <th>Danh mục hàng</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tên sản phẩm</th>
                                                <th>Mô tả</th>
                                                <th>Giá</th>
                                                <th>Ảnh</th>
                                                <th>Danh mục hàng</th>
                                                <th>Action</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach items="${listP}" var="p">
                                                <tr>
                                                    <td>${p.product_id}</td>
                                                    <td>${p.product_name}</td>
                                                    <td>${p.description}</td>
                                                    <td>
                                                        <fmt:formatNumber type="currency" currencyCode="VND"
                                                            value="${p.price}" />
                                                    </td>
                                                    <td><img src="./shoesImg/${p.image_url}" width="100" height="100">
                                                    </td>
                                                    <td>${p.category_id}</td>
                                                    <td>
                                                        <button type="button" class="btn-edit card mb-4">
                                                            <a href="updateProduct?product_id=${p.product_id}">
                                                                <i class="fa-solid fa-pen-to-square"></i> Sửa
                                                            </a>
                                                        </button>

                                                        <button class="btn-delete card mb-4">
                                                            <a onclick="return confirm('bạn có muốn xóa sản phẩm này không?');"
                                                                href="deleteProduct?product_id=${p.product_id}">
                                                                <i class="fa-solid fa-trash-can"></i> Xóa
                                                            </a>
                                                        </button>

                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
                <div class="modal js-modal-add">
                    <div class="modal-container js-modal-add-container">

                        <div class="modal-close js-modal-add-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>

                        <header class="modal-header">
                            Thêm Sản Phẩm
                        </header>

                        <div class="modal-body">
                            <form action="addProduct" method="post" enctype="multipart/form-data">

                                <div class="mb-3 mt-3">
                                    <label for="product_name" class="modal-lable">Tên sản phẩm:</label>
                                    <input type="text" class="modal-input" placeholder="Nhập tên sản phẩm"
                                        name="product_name" required>
                                </div>
                                <div class="mb-3 mt-3">
                                    <label for="description" class="modal-lable">Mô tả:</label>
                                    <input type="text" class="modal-input" placeholder="Nhập mô tả sản phẩm"
                                        name="description" required>
                                </div>

                                <div class="mb-3">
                                    <label for="DOB" class="modal-lable">Giá tiền:</label>
                                    <input type="text" class="modal-input" placeholder="Nhập giá sản phẩm" name="price"
                                        required>
                                </div>

                                <div class="mb-3 mt-3">
                                    <label for="image" class="modal-lable">Ảnh sản phẩm:</label>
                                    <input type="file" class="modal-input" name="image" required>
                                </div>

                                <label for="category_id" class="modal-lable">Danh mục hàng:</label>
                                <select class="modal-input" name="category_id" required>
                                    <c:forEach items="${listC}" var="category">
                                        <option value="${category.category_id}">${category.category_name}</option>
                                    </c:forEach>
                                </select><br>


                                <button type="submit" class="btn-add-doctor">Thêm sản phẩm</button>
                            </form>
                        </div>
                    </div>

                    <div class="open"></div>
                </div>
                <script src="js/listProducts.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="js/scripts.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                    crossorigin="anonymous"></script>
                <script src="assets/demo/chart-area-demo.js"></script>
                <script src="assets/demo/chart-bar-demo.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
                    crossorigin="anonymous"></script>
                <script src="js/datatables-simple-demo.js"></script>
            </body>

            </html>