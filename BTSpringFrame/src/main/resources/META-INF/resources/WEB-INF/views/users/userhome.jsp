<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ Người Dùng</title>
    </head>
<body>

    <c:set var="user" value="${sessionScope.user}" />

    <h1>👋 Xin Chào, ${user.fullname}</h1>
    <p>Đây là trang chủ dành cho người dùng có vai trò USER.</p>

    <hr>
    
    <p>
        <a href="<c:url value='/logout' />">Đăng xuất</a>
    </p>

</body>
</html>