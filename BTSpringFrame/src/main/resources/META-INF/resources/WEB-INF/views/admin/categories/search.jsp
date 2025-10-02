<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Categories</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .pagination {
            justify-content: center;
            margin-top: 20px;
        }
        .table thead th {
            background-color: #f8f9fa; /* Light gray header */
        }
        .table td, .table th {
            vertical-align: middle; /* Center content vertically */
        }
        .action-buttons a {
            margin: 0 5px;
        }
    </style>
</head>
<body>
    <jsp:include page="../layout/header.jsp"/>

    <section class="row">
        <div class="col mt-4">
            <div class="card">
                <div class="card-header">
                    <h4>Search Results for Categories</h4>
                    <%-- Form Search (giống list.jsp) --%>
                    <form action="${pageContext.request.contextPath}/admin/categories/search" method="get" class="form-inline">
                        <div class="form-group mr-2">
                            <input type="text" name="name" class="form-control" placeholder="Search by name..." value="${param.name}">
                        </div>
                        <button type="submit" class="btn btn-outline-primary"><i class="fas fa-search"></i> Search</button>
                        <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-outline-secondary ml-2"><i class="fas fa-times"></i> Clear</a>
                    </form>
                    <a href="${pageContext.request.contextPath}/admin/categories/add" class="btn btn-success float-right"><i class="fas fa-plus"></i> Add New Category</a>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Category ID</th>
                                <th>Category Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty categories}">
                                    <tr>
                                        <td colspan="3" class="text-center">No categories found matching your criteria.</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${categories}" var="category">
                                        <tr>
                                            <td>${category.id}</td>
                                            <td>${category.categoryName}</td>
                                            <td class="action-buttons">
                                                <a href="${pageContext.request.contextPath}/admin/categories/view/${category.id}" class="btn btn-sm btn-outline-info" title="View"><i class="fa fa-info-circle"></i></a>
                                                <a href="${pageContext.request.contextPath}/admin/categories/edit/${category.id}" class="btn btn-sm btn-outline-warning" title="Edit"><i class="fa fa-edit"></i></a>
                                                <a href="${pageContext.request.contextPath}/admin/categories/delete/${category.id}" class="btn btn-sm btn-outline-danger" title="Delete" onclick="return confirm('Are you sure you want to delete this category?');"><i class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <%-- Pagination controls would typically be here, similar to list.jsp if search results are paginated --%>
            </div>
        </div>
    </section>

    <jsp:include page="../layout/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>