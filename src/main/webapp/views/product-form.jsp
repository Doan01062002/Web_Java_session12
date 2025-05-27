<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Form</title>
</head>
<body>
<h2>${product != null && product.id > 0 ? "Edit Product" : "Add New Product"}</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="products" method="post">
    <input type="hidden" name="id" value="${product.id}"/>
    Name: <input type="text" name="name" value="${product.name}" required/><br/><br/>
    Price: <input type="number" step="0.01" name="price" value="${product.price}" required/><br/><br/>
    Quantity: <input type="number" name="quantity" value="${product.quantity}" required/><br/><br/>
    Image URL: <input type="text" name="image" value="${product.image}"/><br/><br/>
    <button type="submit">Save</button>
</form>
<br/>
<a href="products">Back to list</a>
</body>
</html>
