<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product</title>
</head>
<body>
<h1>Add Product</h1>
<c:out value="${message}" /><br/>
<form action="addProduct" name="addProduct" method="post">

<table>
<tr>
<td>Product Name:</td>
<td><input type="text" name="productName" id="productName" /></td>
</tr>

<tr>
<td>Category:</td>
<td>
<select name="category">
<option value="1">Electronic Appliances</option>
<option value="2">Books</option>
<option value="3">Home, Kitchen</option>
<option value="4">Sports & Fitness</option>
<option value="5">Mobile & Computers</option>
</select></td>
</tr>

<tr>
<td>Price:</td>
<td><input type="text" name="price" id="price" /></td>
</tr>

<tr>
<td>Stock</td>
<td><input type="text" name="stock" id="stock" /></td>
</tr>

<tr>
<td>Remarks</td>
<td><input type="text" name="remarks" id="remarks" /></td>
</tr>

<tr>
<td><input type="submit" value="Add"></td>
<td><input type="button" value="Cancel" onclick="window.location.href='index.jsp'"> </td>
</tr>
</table>

</form>
</body>
</html>