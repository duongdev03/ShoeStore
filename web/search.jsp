<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Kết quả tìm kiếm</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
                <link rel="stylesheet" href="./css/search.css">
            </head>

            <body>
                <!-- Header -->
                <section id="header">
                    <a href="shop"><img src="logo/LogoStore.png" class="logo" alt="Logo"></a>
                    <div id="search-bar">
                        <form action="search" method="get" class="d-flex">
                            <input type="text" name="query" class="form-control" placeholder="Tìm kiếm sản phẩm..."
                                required>
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </div>
                </section>

                <!-- Bộ lọc -->
                <section id="filters" class="container mt-4">
                    <form action="search" method="get" class="row">
                        <input type="hidden" name="query" value="${param.query}">
                        <div class="col-md-3">
                            <label for="category">Danh mục:</label>
                            <select name="category" id="category" class="form-select">
                                <option value="">Tất cả</option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.category_id}" ${param.category eq category.category_id
                                        ? 'selected' : '' }>
                                        ${category.category_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="price">Khoảng giá:</label>
                            <select name="price" id="price" class="form-select">
                                <option value="">Tất cả</option>
                                <option value="0-50000" ${param.price eq '0-50000' ? 'selected' : '' }>Dưới 50,000 VND
                                </option>
                                <option value="50000-200000" ${param.price eq '50000-200000' ? 'selected' : '' }>50,000
                                    - 200,000 VND</option>
                                <option value="200000-500000" ${param.price eq '200000-500000' ? 'selected' : '' }>
                                    200,000 - 500,000 VND</option>
                                <option value="500000" ${param.price eq '500000' ? 'selected' : '' }>Trên 500,000 VND
                                </option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="sort">Sắp xếp:</label>
                            <select name="sort" id="sort" class="form-select">
                                <option value="">Mặc định</option>
                                <option value="price-asc" ${param.sort eq 'price-asc' ? 'selected' : '' }>Giá tăng dần
                                </option>
                                <option value="price-desc" ${param.sort eq 'price-desc' ? 'selected' : '' }>Giá giảm dần
                                </option>
                            </select>
                        </div>
                        <div class="col-md-3 d-flex align-items-end">
                            <button type="submit" class="btn btn-primary w-100">Áp dụng</button>
                        </div>
                    </form>
                </section>

                <!-- Danh sách sản phẩm -->
                <section id="products" class="container mt-4">
                    <div class="row">
                        <c:forEach items="${products}" var="product">
                            <div class="col-md-3 mb-4">
                                <div class="card">
                                    <img src="./shoesImg/${product.image_url}" class="card-img-top"
                                        alt="${product.product_name}">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.product_name}</h5>
                                        <p class="card-text">
                                            <fmt:formatNumber type="currency" currencyCode="VND"
                                                value="${product.price}" />
                                        </p>
                                        <a href="detail?pid=${product.product_id}" class="btn btn-primary">Xem chi
                                            tiết</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>

                <!-- Phân trang -->
                <section id="pagination" class="container mt-4">
                    <nav>
                        <ul class="pagination justify-content-center">
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link"
                                        href="search?query=${param.query}&page=${i}&category=${param.category}&price=${param.price}&sort=${param.sort}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${totalPages == 0}">
                                <div class="text-center text-muted">Không có sản phẩm nào phù hợp.</div>
                            </c:if>
                        </ul>
                    </nav>
                </section>
            </body>

            </html>