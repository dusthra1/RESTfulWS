<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Products</title>
</head>
<body>
<h1>View Product(s)</h1>
<form action="viewProducts" name="viewProduct" method="GET" onsubmit="return validate();">
<table>
<tr>
<td>Product ID: </td>
<td><input type="text" id="productId" name="productId"> </td>
</tr>
<tr>
<td>OR</td>
</tr>
<tr>
<td>Category:</td>
<td>
<select name="category">
<option value="">Select</option>
<option value="1">Electronic Appliances</option>
<option value="2">Books</option>
<option value="3">Home, Kitchen</option>
<option value="4">Sports & Fitness</option>
<option value="5">Mobile & Computers</option>
</select></td>
</tr>
<tr>
<td>OR</td>
</tr>

<tr>
<td>Price: Between</td>
<td><input type="text" id="startRange" name="startRange"> & </td>
<td><input type="text" id="endRange" name="endRange"> INR</td>
</tr>

<tr>
<td>All Products: </td>
<td><input type="checkbox" id="allProducts" name="allProducts"> </td>
</tr>
<tr></tr>
<tr>
<td><input type="submit" value="View"></td>
<td><input type="button" value="Cancel" onclick="window.location.href='index.jsp'"> </td>
</tr>

</table>
</form>


<br/><br/>
<textarea rows="25" cols="60" readonly="readonly">
<c:out value="${results}" />
</textarea>
</body>
<script type="text/javascript">
function validate(){
	var flag = true;
	
	var productId = document.getElementById("productId");
	if(isNaN(productId.value)){
		alert("Please enter numbers only");
		productId.focus();
		flag= false;
	}
	
	var startRange = document.getElementById("startRange");
	var endRange  = document.getElementById("endRange");
	
	if(isNaN(startRange.value)){
		alert("Please enter numbers only");
		startRange.focus();
		flag= false;
	}
	
	if(isNaN(endRange.value)){
		alert("Please enter numbers only");
		endRange.focus();
		flag= false;
	}
	
	
return flag;
}
</script>
</html>