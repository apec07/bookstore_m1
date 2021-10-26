<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style type="text/css">
/*  ul */
/*  { */
/*   border: 0px solid #90bade; */
/*  } */
/*  li */
/*  {  */
/*   list-style-type:none; */
/*   border: 1px solid black; */
/*  } */
/*  .lv1 */
/* { */
/*    margin:0px 0px 0px 10px; */
/* } */

</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/font-awesome-4.5.0/css/font-awesome.css">
<link rel='stylesheet' href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ShoppingCart.css"/>
<title>Order Page</title>
</head>
<body>
	     <OL class="cart_list" id="product_cart_list"> 
                 	    <c:forEach var="cartVO" items="${shoppingcart}" varStatus="s">
                          <LI data-sn='${cartVO.product_no}' data-index='${s.index}'>					      
					       <div class="cart-header"> 
								   <button type="button" id="${cartVO.product_no}" class="delete"><i class="fa fa-trash-o fa-2x"></i>刪除</button>
						     <div class="cart-sec">
							    <div class="cart-item">
								    <img src="<%=request.getContextPath() %>/reader/DBGifReader?product_no=${cartVO.product_no}" class="img-rounded" />
							    </div>
							    
							    <div class="cart-item-info">
								    <h3>${cartVO.prod_name}<span>Model No: ${cartVO.product_no}</span></h3>
								    <h4 class="price"><span>NT.$ </span><em>${cartVO.prod_price}</em></h4>
								    <p class="qty">減少數量</p>
								    <div class='quantity_minus'><i class="fa fa-minus-circle"></i></div>&nbsp;
								    <input  type='text' value="${cartVO.cart_mount}" class="form-control" readonly>
                                    <div class='quantity_plus'> <i class="fa fa-plus-circle"></i></div> 
							        <p class="qty">增加數量</p>
							    </div> 
							    
							    <div class="delivery">
								    <span>需要2-3個工作天</span><div class="clearfix"></div>
							    </div>
						     </div>
					       </div>
                         </LI>
                       </c:forEach>
                     </OL> 

<!-- <HR> -->
<!-- 	<OL> -->
<!-- 	<li><input type='button' id='btn1' value='a'><input type='text' placeholder='a input'></li> -->
<!-- 	<li><input type='button' id='btn2' value='b'></li> -->
<!-- 	<li><input type='button' id='btn3' value='c'></li> -->
<!-- 	<li><input type='button' id='btn4' value='d'></li> -->
<!-- 	</OL> -->
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