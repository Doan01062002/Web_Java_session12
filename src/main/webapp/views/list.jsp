
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Student List</h2>
<a href="student?action=new">Add New</a>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Email</th><th>DOB</th><th>Actions</th></tr>
    <c:forEach var="s" items="${list}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td>${s.dob}</td>
            <td>
                <a href="student?action=edit&id=${s.id}">Edit</a>
                <a href="student?action=delete&id=${s.id}" onclick="return confirm('Delete this student?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
