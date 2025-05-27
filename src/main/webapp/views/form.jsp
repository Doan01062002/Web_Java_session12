
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="student" method="post">
    <input type="hidden" name="id" value="${student.id}"/>
    Name: <input type="text" name="name" value="${student.name}" required/><br/>
    Email: <input type="email" name="email" value="${student.email}" required/><br/>
    DOB: <input type="date" name="dob" value="${student.dob}" required/><br/>
    <button type="submit">Save</button>
</form>
<a href="student">Back</a>
</body>
</html>
