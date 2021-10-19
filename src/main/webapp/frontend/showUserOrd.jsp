<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style type="text/css">
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
<title>Order Page</title>
</head>
<body>
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
	<OL>
	<li><input type='button' id='btn1' value='a'><input type='text' placeholder='a input'></li>
	<li><input type='button' id='btn2' value='b'></li>
	<li><input type='button' id='btn3' value='c'></li>
	<li><input type='button' id='btn4' value='d'></li>
	</OL>
<script>
	
		document.getElementById("btn1").addEventListener("click",function(){
	  		swal("Good job!", "You clicked the button!", "success");
		});
		
		document.getElementById("btn2").addEventListener("click",function(){
	  		swal("Good job!", "You clicked the button!", "error");
		});
		
		document.getElementById("btn3").addEventListener("click",function(){
	  		swal("Good job!", "You clicked the button!", "warning");
		});
		
		document.getElementById("btn4").addEventListener("click",function(){
	  		swal("Good job!", "You clicked the button!", "info");
		});
</script>
</body>


</html>