<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách sinh viên</title></head>
<body>
<h2>Danh sách sinh viên</h2>

<form action="${pageContext.request.contextPath}/students/search" method="get">
    <input type="text" name="keyword" placeholder="Nhập tên cần tìm..."/>
    <button type="submit">Tìm kiếm</button>
</form>

<a href="${pageContext.request.contextPath}/students/add">+ Thêm mới</a>

<table border="1">
    <tr>
        <th>ID</th><th>Họ tên</th><th>Email</th><th>SĐT</th><th>Giới tính</th><th>Trạng thái</th><th>Hành động</th>
    </tr>
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.studentId}</td>
            <td>${s.fullName}</td>
            <td>${s.email}</td>
            <td>${s.phoneNumber}</td>
            <td>${s.gender ? 'Nam' : 'Nữ'}</td>
            <td>${s.accountStatus}</td>
            <td>
                <a href="${pageContext.request.contextPath}/students/edit/${s.studentId}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/students/delete/${s.studentId}" onclick="return confirm('Xóa thật hả?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
