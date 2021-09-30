<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read cart list from product -->
<%@ page import="idv.cart.model.CartVO" %>
<!-- utility  -->
<%@ page import="java.util.*" %>
<jsp:useBean id="cart_Svc" scope="page" class="idv.cart.model.CartService" />
<jsp:useBean id="prod_Svc" scope="page" class="idv.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
<style>
/* ChecK CSS style  */
	.vertical-center {
  		display: flex;
    	justify-content: center; 
    	align-items: center;
    	padding: 70px 0;
    	text-align: center; 
	}
	.center {
    padding: 70px 0;
    border: 3px solid green;
}
</style>
</head>
<body>

<table border=1>
	<c:if test="${customer!=null }" >
	<thead>
<%-- 		<c:forEach items="${cart_Svc.cartHeader}" var="header_name"> --%>
<%-- 		<th>${header_name}</th> --%>
<%-- 		</c:forEach> --%>
		<c:if test="${shoppingcart !=null }" >
			<th>Product Name</th>
			<th>Product Price</th>
			<th>Product Picture</th>
			<th>Product Mount</th>
			<th>Action</th>		
		</c:if>
		<c:if test="${shoppingcart ==null }" >
			<p><Strong>No Cart List</Strong></p>
		</c:if>
	</thead>
	<tbody>
	<!-- retrieve user id  -->

		<c:if test="${shoppingcart !=null }" >
		<c:forEach items="${shoppingcart }" var="cart">
		<tr>
<%-- 			<td>${cart.customer_no }</td> --%>
			
			
			<c:forEach var="prod" items="${prod_Svc.allProd}">
                    <c:if test="${cart.product_no==prod.product_no}">
	                    <td>${prod.prod_name}</td>
	                    <td>${prod.prod_price}</td>
	                    <td><img width='100' src="${pageContext.request.contextPath}/reader/DBGifReader?product_no=${prod.product_no}"/></td>
                    </c:if>
            </c:forEach>
			<td class="vertical-center" >${cart.cart_mount }</td>
			<td>
				<div class="vertical-center">
				<input type="button" value="Remove">
				<input type="button" value="Plus">
				<input type="button" value="Minus">
				</div>
			</td>
		</tr>
		</c:forEach>
		</c:if>
	</tbody>
	</c:if>
</table>
</body>
</html>