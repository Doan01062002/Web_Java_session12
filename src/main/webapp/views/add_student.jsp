<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><c:out value="${student.studentId == null ? 'Thêm' : 'Sửa'} Sinh viên"/></title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
<h2><c:out value="${student.studentId == null ? 'Thêm mới' : 'Cập nhật'} thông tin sinh viên"/></h2>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<form:errors path="*" cssClass="error"/>
<form:form method="post" modelAttribute="student" action="${pageContext.request.contextPath}/students/${student.studentId == null ? 'add' : 'edit'}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    Mã sinh viên: <form:input path="studentId" readonly="${student.studentId != null}"/><br/>
    <form:errors path="studentId" cssClass="error"/><br/>
    Họ tên: <form:input path="fullName"/><br/>
    <form:errors path="fullName" cssClass="error"/><br/>
    Email: <form:input path="email"/><br/>
    <form:errors path="email" cssClass="error"/><br/>
    Số điện thoại: <form:input path="phoneNumber"/><br/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>
    Giới tính:
    <form:radiobutton path="gender" value="true"/> Nam
    <form:radiobutton path="gender" value="false"/> Nữ<br/>
    <form:errors path="gender" cssClass="error"/><br/>
    Ngày sinh: <form:input path="birthDate" type="date"/><br/>
    <form:errors path="birthDate" cssClass="error"/><br/>
    Hình ảnh: <form:input path="profilePicture"/><br/>
    <form:errors path="profilePicture" cssClass="error"/><br/>
    Trạng thái:
    <form:select path="accountStatus">
        <form:option value="ACTIVE" label="Hoạt động"/>
        <form:option value="INACTIVE" label="Ngưng hoạt động"/>
    </form:select><br/>
    <form:errors path="accountStatus" cssClass="error"/><br/>
    <button type="submit">Lưu</button>
</form:form>
<a href="${pageContext.request.contextPath}/students">← Quay lại danh sách</a>
</body>
</html>