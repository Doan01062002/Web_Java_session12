<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/26/2025
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Danh sách xe</h2>
<a href="add">Thêm xe</a>
<table>
    <tr><th>Biển số</th><th>Loại xe</th><th>Tổng ghế</th><th>Chi tiết</th><th>Xóa</th></tr>
    <c:forEach var="bus" items="${buses}">
        <tr>
            <td>${bus.licensePlate}</td>
            <td>${bus.busType}</td>
            <td>${bus.totalSeat}</td>
            <td><a href="detail?id=${bus.id}">Xem</a></td>
            <td><a href="edit?id=${bus.id}">Sửa</a></td>
            <td><a href="delete?id=${bus.id}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
