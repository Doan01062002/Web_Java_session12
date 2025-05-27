<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/26/2025
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Chi tiết xe: ${bus.licensePlate}</h2>
<img src="${bus.image}" width="200px"/>
<p>Loại: ${bus.busType} - Tổng ghế: ${bus.totalSeat}</p>
<h3>Danh sách ghế:</h3>
<table>
    <c:forEach var="row" begin="0" end="${bus.rowSeat - 1}">
        <tr>
            <c:forEach var="col" begin="0" end="${bus.colSeat - 1}">
                <td>
                    <c:set var="index" value="${row * bus.colSeat + col}" />
                    <c:if test="${index lt seats.size()}">
                        ${seats[index].nameSeat} (${seats[index].status})
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>

</body>
</html>
