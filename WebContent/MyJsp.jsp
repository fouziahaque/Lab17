<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>List of customers who are currently subscribed</h2>

<table>
<c:forEach items="${list}" var="customer">
<tr>
<td>${customer.firstName}</td>
<td>${customer.lastName}</td>
<td>${customer.email}</td>


</tr>
</c:forEach>
</table>


<button onclick="goBack()">Go Back</button>

<script>
function goBack() {
    window.history.back();
}
</script>

</body>
</html>