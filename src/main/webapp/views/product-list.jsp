<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sách sản phẩm</title>
</head>
<body>
<h2>Product List</h2>
<a href="products?action=new">Add New Product</a>
<br/><br/>
<table border="1" cellpadding="8" cellspacing="0">
  <tr>
    <th>ID</th><th>Name</th><th>Price</th><th>Quantity</th><th>Image</th><th>Actions</th>
  </tr>
  <c:forEach var="p" items="${list}">
    <tr>
      <td>${p.id}</td>
      <td>${p.name}</td>
      <td>${p.price}</td>
      <td>${p.quantity}</td>
      <td><img src="${p.image}" alt="${p.name}" width="80"/></td>
      <td>
        <a href="products?action=edit&id=${p.id}">Edit</a> |
        <a href="products?action=delete&id=${p.id}" onclick="return confirm('Bạn muốn xoá sản phẩm này?')">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
