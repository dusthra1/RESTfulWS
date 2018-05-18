<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Product</title>
</head>
<body>
<h1>Delete Product</h1>
<c:out value="${message}" /><br/>
<form action="deleteProduct" name="getProduct" method="GET">
<table>
<tr>
<td>Product ID: </td>
<td><input type="text" id="productId" name="productId"> </td>
</tr>

<tr>
<td><input type="submit" value="View"></td>
<td><input type="button" value="Cancel" onclick="window.location.href='index.jsp'"> </td>
</tr>

</table>
</form>


<form action="deleteProduct" name="deleteProduct" method="POST">
<table>
<tr>
<td>
<input type="hidden" name="productId" id="productId" value="${productId}" />
<textarea rows="15" cols="30" id="productxml" name="productxml">
<c:out value="${results}" />
</textarea>
<i>* Please change the parameters you want to update</i>
</td>
</tr>

<tr>
<td><input type="submit" value="Delete"></td>

</tr>

</table>
</form>
</body>
</html>