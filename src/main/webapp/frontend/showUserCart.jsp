<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read cart list from product -->
<%@ page import="idv.cart.model.CartVO" %>
<!-- utility  -->
<%@ page import="java.util.List" %>
<jsp:useBean id="cart_Svc" scope="page" class="idv.cart.model.CartService" />

<!DOCTYPE html>
<html>
<head>
<!-- Set User Session for Test!!! -->
<%-- <jsp:setProperty property="customer_no" value="1" name="CartVO"/> --%>
<%
	CustomerVO customer = (CustomerVO)session.getAttribute("customer");
	
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
		<c:forEach items="<%=cart_Svc.getMyCart(customer.getNo()) %>" var="cart">
		<tr>
			<td>${cart.customer_no }</td>
			<td>${cart.product_no }</td>
			<td>${cart.cart_mount }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>