<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read cart list from product -->
<%@ page import="idv.cart.model.CartVO" %>
<!-- utility  -->
<%@ page import="java.util.*" %>
<jsp:useBean id="cart_Svc" scope="page" class="idv.cart.model.CartService" />

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
			<td><strong>Payed</strong></td><td colspan="2">
					<div><input type='radio' name='ord_status' value=1 >已付款</div>
					<div><input type='radio' name='ord_status' value=0 checked>未付款</div></td>
		</tr>
		<tr>
			<td><strong>Receiver</strong></td><td colspan="2"><input type="text" name="receiver"></td>
		</tr>
		<tr>
			<td><strong>REC_PHONE</strong></td><td colspan="2"><input type="text" name="rec_phone"></td>
		</tr>
		<tr>
			<td><strong>REC_ZIP</strong></td><td colspan="2"><input type="text" name="rec_zip"></td>
		</tr>
		<tr>
			<td><strong>REC_ADDRESS</strong></td><td colspan="2"><textarea name="rec_address"></textarea></td>
		</tr>
	</table>
	<ul class="borderlist">
		<c:forEach var="cart" items="${shoppingcart}" varStatus="s">
	
			<li class="lv1" data-index="${s.index}">
				<input type="hidden" name="product_no" value="${cart.product_no}">
				<input type="hidden" name="quantity" value="${cart.cart_mount}">
				<div><strong>Name - </strong>${cart.prod_name}</div>
				<div><strong>Price - </strong><span class="cart_price">${cart.prod_price}</span></div>
				<div><img width='100' src="${pageContext.request.contextPath}/reader/DBGifReader?product_no=${cart.product_no}"/></div>
				<div><strong>Count - </strong><span class="cart_mount">${cart.cart_mount}</span></div>
<!-- 				try made into span html -->
				<input type="hidden" name ="prod_price" value="${prod.prod_price}">
			</li>
			
		</c:forEach>
		<li><div><h2>Total Cost - </h2><h3><span class="cart_total_price">0</span></h3></div></li>
	</ul>
	<input type="hidden" name="action" value="newOrder">
	<input type="hidden" name=ord_total class="cart_total_price" >
	<input type='submit'>
	<input type='reset'>
	</form>
	</c:if>
<script>
// cal total cost and put in
		window.onload = function() {
  			
		};
	   var prod_prices = document.getElementsByClassName("cart_price");
	   var cart_mount = document.getElementsByClassName("cart_mount");
	   var total = 0;
	   for(i=0;i<prod_prices.length;i++){
		   console.log(prod_prices[i].innerHTML);
		   total += prod_prices[i].innerHTML * cart_mount[i].innerHTML;
	   }
	   console.log("totoal = "+total);
	   if(cart_mount.length>0){
		   //span set total price - OK
		   var x = document.getElementsByClassName("cart_total_price")[0].innerHTML = total;
		   //input set total price - OK!
		   document.getElementsByClassName("cart_total_price")[1].setAttribute('value',total);
	   }
	   
	   
	   
	  
		
</script>
</body>
</html>