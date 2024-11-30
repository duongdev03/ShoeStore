<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Danh sách hàng tồn kho</title>
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
                <main>
                    <div class="container-fluid px-4">
                        <button type="button" class="btn btn-success btn-add js-addBS">
                            <a
                                href="#">
                                THÊM MỚI
                            </a>
                        </button>
                    </div>
                    <div class="container-fluid px-4">   
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Thống Kê Hàng Tồn Kho
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>size</th>
                                        <th>Số lượng</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>size</th>
                                        <th>Số lượng</th>
                                        <th>Action</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach items="${listPS}" var="ps">
                                        <tr>
                                            <td>${ps.product_size_id}</td>
                                            <td>${ps.product_id}</td>
                                            <td>${ps.product_name}</td>
                                            <td>${ps.size}</td>
                                            <td>${ps.stock_quantity}</td>
                                            <td>
                                                <button type="button" class="btn-edit card mb-4">
                                                    <a
                                                        href="updateWarehouse?product_size_id=${ps.product_size_id}">
                                                        <i class="fa-solid fa-pen-to-square"></i> Sửa
                                                    </a>
                                                </button>

                                                <button class="btn-delete card mb-4">
                                                    <a
                                                        onclick="return confirm('bạn có muốn xóa size giày này không?');" href="deleteProductSize?product_size_id=${ps.product_size_id}">
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
                    Thêm mới kho
                </header>

                <div class="modal-body"> 
                    <form action="addStockQuantity" method="post">

                        <div class="mb-3 mt-3">
                            <label for="product_name" class="modal-lable">Tên sản phẩm:</label>
                            <select class="modal-input" name="product_id" required>
                            <c:forEach items="${listProd}" var="product">
                                <option value="${product.product_id}">${product.product_name}</option>
                            </c:forEach>
                        </select><br>
                        </div>
                        <div class="mb-3 mt-3">
                            <label for="size" class="modal-lable">Size:</label>
                            <input type="text" class="modal-input" placeholder="Nhập size sản phẩm" name="Psize" required>
                        </div>

                        <div class="mb-3">
                            <label for="quantity" class="modal-lable">Số lượng:</label>
                            <input type="text" class="modal-input" placeholder="Nhập số lượng hàng" name="stock_quantity" required>
                        </div>
          
                        <button type="submit" class="btn-add-doctor">Thêm sản phẩm</button>
                    </form>
                </div>
            </div>

            <div class="open"></div>
        </div>
        <script src="js/listProducts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>


