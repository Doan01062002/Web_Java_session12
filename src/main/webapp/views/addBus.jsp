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
<h2>Thêm xe khách</h2>
<form method="post" action="add">
    Biển số: <input name="licensePlate"/><br/>
    Loại xe: <input name="busType"/><br/>
    Số hàng ghế: <input name="rowSeat" type="number"/><br/>
    Số cột ghế: <input name="colSeat" type="number"/><br/>
    Ảnh: <input name="image"/><br/>
    <button type="submit">Thêm</button>
</form>
<p style="color:red">${error}</p>

</body>
</html>
