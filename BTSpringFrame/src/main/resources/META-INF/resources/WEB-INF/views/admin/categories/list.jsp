```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Danh Mục</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .header-section { background-color: #343a40; color: white; padding: 10px 20px; margin-bottom: 20px; }
        .table thead th { background-color: #f8f9fa; }
        .table td, .table th { vertical-align: middle; }
        .action-buttons a { margin: 0 5px; }
        .footer-section { background-color: #f8f9fa; padding: 10px 20px; text-align: center; margin-top: 20px; }
    </style>
</head>
<body>
    <div class="header-section">
        <h4><i class="fas fa-list"></i> Danh Sách Danh Mục</h4>
    </div>

    <section class="row">
        <div class="col mt-4">
            <div class="card">
                <div class="card-header">
                    <h4>Danh Sách Danh Mục</h4>
                    <form action="${pageContext.request.contextPath}/admin/categories/search" method="get" class="form-inline">
                        <div class="form-group mr-2">
                            <input type="text" name="name" class="form-control" placeholder="Tìm kiếm theo tên..." value="${searchName}">
                        </div>
                        <button type="submit" class="btn btn-outline-primary"><i class="fas fa-search"></i> Tìm kiếm</button>
                        <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-outline-secondary ml-2"><i class="fas fa-times"></i> Xóa bộ lọc</a>
                    </form>
                    <a href="${pageContext.request.contextPath}/admin/categories/add" class="btn btn-success float-right"><i class="fas fa-plus"></i> Thêm Danh Mục Mới</a>
                </div>
                <div class="card-body">
                    <c:if test="${message != null}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <strong>Thành công!</strong> ${message}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>Lỗi!</strong> ${error}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Mã Danh Mục</th>
                                <th>Tên Danh Mục</th>
                                <th>Hình Ảnh</th>
                                <th>Hành Động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty categories}">
                                    <tr>
                                        <td colspan="4" class="text-center">Không tìm thấy danh mục nào.</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${categories}" var="category">
                                        <tr>
                                            <td>${category.categoryID}</td>
                                            <td>${category.categoryName}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${empty category.images}">
                                                        <img height="150" width="200" src="${pageContext.request.contextPath}/images/default.jpg" alt="Không có hình ảnh" />
                                                    </c:when>
                                                    <c:when test="${fn:startsWith(category.images, 'https')}">
                                                        <c:set var="imgUrl" value="${category.images}" />
                                                        <img height="150" width="200" src="${imgUrl}" alt="Hình ảnh danh mục" onerror="this.src='${pageContext.request.contextPath}/images/default.jpg'" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:url value="/image?fname=${category.images}" var="imgUrl" />
                                                        <img height="150" width="200" src="${imgUrl}" alt="Hình ảnh danh mục" onerror="this.src='${pageContext.request.contextPath}/images/default.jpg'" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="action-buttons">
                                                <a href="${pageContext.request.contextPath}/admin/categories/view/${category.categoryID}" class="btn btn-sm btn-outline-info"><i class="fa fa-info-circle"></i></a>
                                                <a href="${pageContext.request.contextPath}/admin/categories/edit/${category.categoryID}" class="btn btn-sm btn-outline-warning"><i class="fa fa-edit"></i></a>
                                                <a href="${pageContext.request.contextPath}/admin/categories/delete/${category.categoryID}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?');"><i class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer text-muted">
                    <!-- Phân trang bị loại bỏ vì dùng findAll -->
                </div>
            </div>
        </div>
    </section>

    <div class="footer-section">
        <p>&copy; 2025 Ứng dụng của bạn. Bảo lưu mọi quyền.</p>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
```