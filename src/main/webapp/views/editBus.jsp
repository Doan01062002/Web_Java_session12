<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa thông tin xe</title>
</head>
<body>
<h2>Sửa thông tin xe</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/bus/update" method="post">
    <input type="hidden" name="id" value="${bus.id}" />

    Biển số xe: <input type="text" name="licensePlate" value="${bus.licensePlate}" required /><br/>

    Loại xe:
    <select name="busType">
        <option value="NORMAL" ${bus.busType == 'NORMAL' ? 'selected' : ''}>NORMAL</option>
        <option value="VIP" ${bus.busType == 'VIP' ? 'selected' : ''}>VIP</option>
        <option value="LUXURY" ${bus.busType == 'LUXURY' ? 'selected' : ''}>LUXURY</option>
    </select><br/>

    Hàng ghế: <input type="number" name="rowSeat" value="${bus.rowSeat}" required /><br/>
    Cột ghế: <input type="number" name="colSeat" value="${bus.colSeat}" required /><br/>
    Hình ảnh (URL): <input type="text" name="image" value="${bus.image}" required /><br/>

    <button type="submit">Cập nhật</button>
</form>
</body>
</html>
