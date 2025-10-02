<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Category</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .card {
            margin-top: 20px;
        }
        .card-header h2 {
            margin-bottom: 0;
        }
        .card-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .card-footer a {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <section class="row">
        <div class="col-6 offset-3 mt-4">
            <div class="card">
                <div class="card-header">
                    <h2>Edit Category</h2>
                </div>
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath}/admin/categories/saveOrUpdate" method="POST" modelAttribute="category">
                        <div class="mb-3">
                            <label for="categoryID" class="form-label">Category ID:</label>
                            <form:input type="text" readonly="true" class="form-control" id="categoryID" path="categoryID" aria-describedby="categoryId" placeholder="Auto generated"/>
                            <%-- ID is readonly, so no errors displayed --%>
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label">Category Name:</label>
                            <form:input type="text" class="form-control" id="categoryName" path="categoryName" aria-describedby="nameId" placeholder="Enter Category Name"/>
                            <form:errors path="categoryName" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="images" class="form-label">Image URL:</label>
                            <form:input type="text" class="form-control" id="images" path="images" aria-describedby="imagesId" placeholder="Enter Image URL"/>
                            <form:errors path="images" cssClass="text-danger" />
                        </div>
                        <div class="card-footer text-muted">
                            <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> <span>Update</span></button>
                            <a href="${pageContext.request.contextPath}/admin/categories/add" class="btn btn-secondary"><i class="fas fa-plus"></i> New</a>
                            <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-info"><i class="fas fa-bars"></i> List Categories</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>