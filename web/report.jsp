<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Báo Cáo Thống kê</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./css/styles.css" rel="stylesheet" />
        <link href="./css/listproducts.css" rel="stylesheet" />
        <link href="./css/report.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="menu.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="row mt-4">
                            <div class="col-xl-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-header">
                                        Doanh thu tháng
                                    </div>
                                    <div class="card-body">${revenue}</div>
                            </div>
                        </div>
                        <div class="col-xl-6">
                            <div class="card bg-primary text-white mb-4">
                                <div class="card-header">
                                    Sản phẩm bán chạy nhất
                                </div>
                                <div class="card-body">${bestSellingProduct}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col-xl-6">
                            <div class="card bg-danger text-white mb-4">
                                <div class="card-header">
                                    Số lượng đơn hủy
                                </div>
                                <div class="card-body">${cancelledOrdersCount}</div>                               
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container-fluid px-4">   
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Thống kê doanh thu
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>Năm</th>
                                        <th>Tháng</th>
                                        <th>Tổng doanh thu</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <tr>
                                        <th>Năm</th>
                                        <th>Tháng</th>
                                        <th>Tổng doanh thu</th>
                                    </tr>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach items="${monthlyRevenueList}" var="revenue">
                                        <tr>
                                            <td>${revenue.year}</td>
                                            <td>${revenue.month}</td>
                                            <td><fmt:formatNumber type="currency" currencyCode="VND" value="${revenue.revenue}" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>            
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


