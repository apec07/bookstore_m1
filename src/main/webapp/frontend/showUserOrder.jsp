<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read order from product -->
<%@ page import="idv.ord.model.OrdVO" %>
<%@ page import="idv.ord_details.model.Ord_detailsVO" %>
<%@ page import="idv.product.model.ProductVO" %>
<!-- utility  -->
<%@ page import="java.util.List" %>

<jsp:useBean id="cart_Svc" scope="page" class="idv.cart.model.CartService" />
<jsp:useBean id="ord_Svc" scope="page" class="idv.ord.model.OrdService"/>


<!DOCTYPE html>
<html>
<head>
<title>Order List or Cart List</title>
<%
// 	CustomerVO customer = session.setAttribute("customer", );
%>
</head>
<body>

	<H2>Order List</H2>
	
	<table border="1">
		<thead>
		<c:forEach items="${ord_Svc.ordHeader}" var="header_name">
			<th><div style="color:blue;text-align:center;width:100px;">${header_name}</div></th>
		</c:forEach>
		</thead>
		<tbody>
			<c:forEach items="<%=ord_Svc.getMyOrd(1) %>" var="ord">
			<tr>
			<td><div style="text-align:center;">${ord.ord_no }</div></td>
			<td style="text-align:center;">${ord.customer_no }</td>
			<td style="text-align:center;">${ord.ord_status }</td>
			<td style="text-align:center;">${ord.receiver }</td>
			<td style="text-align:center;">${ord.rec_phone }</td>
			<td style="text-align:center;">${ord.rec_zip }</td>
			<td style="text-align:center;">${ord.rec_address }</td>
			<td style="text-align:center;">${ord.ord_total }</td>
			<td style="text-align:center;">${ord.ord_datetime }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>