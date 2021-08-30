<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read cart list from product -->
<%@ page import="idv.cart.model.CartVO" %>
<jsp:useBean id="cart_Svc" scope="page" class="idv.cart.model.CartService" />

<!DOCTYPE html>
<html>
<head>
<!-- Set User Session for Test!!! -->
<%
	
%>
</head>
<body>

<table border=1>
	<thead>
		<c:forEach items="${cart_Svc.cartHeader}" var="header_name">
		<th>${header_name}</th>
		</c:forEach>
	</thead>
	<tbody>
	<!-- retrieve user id  -->
		<c:forEach items="${cart_Svc.myCart[1]}" var="cart">
			<td>${cart.customer_no }</td>
			<td>${cart.product_no }</td>
			<td>${cart.cart_mount }</td>
		</c:forEach>
	</tbody>
</table>
</body>
</html>