```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Videos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .header-section { background-color: #343a40; color: white; padding: 10px 20px; margin-bottom: 20px; }
        .table thead th { background-color: #f8f9fa; }
        .table td, .table th { vertical-align: middle; }
        .action-buttons a { margin: 0 5px; }
        .footer-section { background-color: #f8f9fa; padding: 10px 20px; text-align: center; margin-top: 20px; }
        .description { max-width: 300px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
        .url { max-width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
    </style>
</head>
<body>
    <div class="header-section">
        <h4><i class="fas fa-list"></i> List Videos</h4>
    </div>

    <section class="row">
        <div class="col mt-4">
            <div class="card">
                <div class="card-header">
                    <h4>List Videos</h4>
                    <form action="${pageContext.request.contextPath}/admin/videos/search" method="get" class="form-inline">
                        <div class="form-group mr-2">
                            <input type="text" name="title" class="form-control" placeholder="Search by title..." value="${searchTitle}">
                        </div>
                        <button type="submit" class="btn btn-outline-primary"><i class="fas fa-search"></i> Search</button>
                        <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-outline-secondary ml-2"><i class="fas fa-times"></i> Clear</a>
                    </form>
                    <a href="${pageContext.request.contextPath}/admin/videos/add" class="btn btn-success float-right"><i class="fas fa-plus"></i> Add New Video</a>
                </div>
                <div class="card-body">
                    <c:if test="${message != null}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <strong>Success!</strong> ${message}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Video ID</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>URL</th>
                                <th>Category</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty videos}">
                                    <tr>
                                        <td colspan="6" class="text-center">No videos found.</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${videos}" var="video">
                                        <tr>
                                            <td>${video.id}</td>
                                            <td>${video.title}</td>
                                            <td class="description" title="${video.description}">${video.description}</td>
                                            <td class="url" title="${video.url}"><a href="${video.url}" target="_blank">${video.url}</a></td>
                                            <td>${video.category.categoryName}</td>
                                            <td class="action-buttons">
                                                <a href="${pageContext.request.contextPath}/admin/videos/view/${video.id}" class="btn btn-sm btn-outline-info"><i class="fa fa-info-circle"></i></a>
                                                <a href="${pageContext.request.contextPath}/admin/videos/edit/${video.id}" class="btn btn-sm btn-outline-warning"><i class="fa fa-edit"></i></a>
                                                <a href="${pageContext.request.contextPath}/admin/videos/delete/${video.id}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Are you sure?');"><i class="fa fa-trash"></i></a>
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
        <p>&copy; 2025 Your Application. All rights reserved.</p>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
```