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
<style type="text/css">
/* ChecK CSS style  */
	.vertical-center {
/*   		display: flex; */
/*     	justify-content: center;  */
/*     	align-items: center; */
/*     	padding: 70px 0; */
/*     	text-align: center;  */
    	vertical-align:middle;
	}
	.center {
    padding: 70px 0;
    border: 3px solid green;
    
      ul
 {
  border: 0px solid #90bade;
 }
 li
 { 
  list-style-type:none;
  border: 1px solid black;
 }
 .lv1
{
   margin:0px 0px 0px 10px;
}


</style>
</head>
<body>
<%--
<table border=1>
	<c:if test="${customer!=null }" >
	<thead>
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
	<tbody class="vertical-center">
	<!-- retrieve user id  -->

		
		<c:if test="${shoppingcart !=null }" >
		<c:forEach items="${shoppingcart }" var="cart">
		<tr>
		
			<c:forEach var="prod" items="${prod_Svc.allProd}">
                    <c:if test="${cart.product_no==prod.product_no}">
	                    <td>${prod.prod_name}</td>
	                    <td>${prod.prod_price}</td>
	                    <td><img width='100' src="${pageContext.request.contextPath}/reader/DBGifReader?product_no=${prod.product_no}"/></td>
                    </c:if>
            </c:forEach>
			<td><input type="number" min="1" value="${cart.cart_mount }"></td>
			<td>
				<div>
				<input type="button" value="Remove">
				<input type="hidden" value="${cart.product_no }">
				<input type="hidden" value="${cart.customer_no }">
				<input type="hidden" value="${cart.cart_mount }">
				<input type="hidden" name="action" value="remove">
				</div>
			</td>
		</tr>
		</c:forEach>
		</c:if>
		
	</tbody>
	</c:if>
</table>
<HR>
 --%>
	<c:if test="${shoppingcart !=null }" >
	<form method="post" action="${pageContext.request.contextPath}/ord.do" >
	<table border=1>
		<tr>
			<td><strong>ORD_NO</strong></td><td><input type="text" name="ord_no"></td>
		</tr>
		<tr>
			<td><strong>Payed</strong></td><td>
					<div><input type='radio' name='ord_status' value=1 >已付款</div>
					<div><input type='radio' name='ord_status' value=0 checked>未付款</div></td>
		</tr>
		<tr>
			<td><strong>Receiver</strong></td><td><input type="text" name="receiver"></td>
		</tr>
		<tr>
			<td><strong>REC_PHONE</strong></td><td><input type="text" name="rec_phone"></td>
		</tr>
		<tr>
			<td><strong>REC_ZIP</strong></td><td><input type="text" name="rec_zip"></td>
		</tr>
		<tr>
			<td><strong>REC_ADDRESS</strong></td><td><textarea name="rec_address"></textarea></td>
		</tr>
	</table>
	<ul class="borderlist">
		<c:forEach var="cart" items="${shoppingcart}">
			<c:forEach var="prod" items="${prod_Svc.allProd}">
				<c:if test="${cart.product_no==prod.product_no}">
			<li class="lv1">
				<input type="hidden" name="product_no" value="${cart.product_no}">
				<input type="hidden" name="quantity" value="${cart.cart_mount}">
				<div>${prod.prod_name}</div>
				<div>${prod.prod_price}</div>
				<div><img width='100' src="${pageContext.request.contextPath}/reader/DBGifReader?product_no=${prod.product_no}"/></div>
				<div>${cart.cart_mount}</div>
			</li>
				</c:if>
			</c:forEach>
		</c:forEach>
		<li><div>Total Cost <span>10000</span></div></li>
	</ul>
	<input type="hidden" name="action" value="newOrder">
	<input type="hidden" name=ord_total value="999">
	<input type='submit'>
	<input type='reset'>
	</form>
	</c:if>
<script>
// cal total cost and put in

</script>
</body>
</html>