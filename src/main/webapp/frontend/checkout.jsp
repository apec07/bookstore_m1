<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read order from product -->
<%@ page import="idv.ord.model.OrdVO" %>
<%@ page import="idv.ord_details.model.Ord_detailsVO" %>
<%@ page import="idv.product.model.ProductVO" %>

<jsp:useBean id="prod_Svc" scope="page" class="idv.product.model.ProductService" />

<%--
--Ord modified by cart--
--------------------------------------------------------
--  for Table ORDER
--------------------------------------------------------
CREATE TABLE ORD (
  ORD_NO      VARCHAR(15) NOT NULL, 
  CUSTOMER_NO SMALLINT NOT NULL,
  ORD_STATUS  TINYINT NOT NULL, 
  RECEIVER    VARCHAR(50), 
  REC_PHONE   VARCHAR(20), 
  REC_ZIP     VARCHAR(10), 
  REC_ADDRESS VARCHAR(200), 
*  ORD_TOTAL   INT, 
*  ORD_DATETIME    TIMESTAMP DEFAULT NOW(),
  CONSTRAINT ORDER_FK FOREIGN KEY (CUSTOMER_NO) REFERENCES customer (NO),
  CONSTRAINT ORDER_PK PRIMARY KEY (ORD_NO));
 
 --------------------------------------------------------
--  for Table ORD_DETAILS
--------------------------------------------------------
CREATE TABLE ORD_DETAILS(	
--  ORD_NO     VARCHAR(15) NOT NULL, 
  PRODUCT_NO SMALLINT NOT NULL,
  QUANTITY   INT,
  PROD_PRICE INT NOT NULL,
  CONSTRAINT ORD_DETAILS_FK1 FOREIGN KEY (ORD_NO) REFERENCES ORD (ORD_NO),
  CONSTRAINT ORD_DETAILS_FK2 FOREIGN KEY (PRODUCT_NO) REFERENCES PRODUCT (PRODUCT_NO),
  CONSTRAINT ORD_DETAILS_PK PRIMARY KEY(ORD_NO, PRODUCT_NO));
 
 
 --%>


<!DOCTYPE html>
<html>
<head>
<title>Simulated Add One Order</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<style>
tfoot tr td div {
	background-color : yellow;
    display: flex;
    justify-content: center; 
    align-items: center; 
}
</style>
</head>
<body>

	<form action='${pageContext.request.contextPath}/ord.do' class='myForm'>
		<table border=1>
			<thead>
			<tr>
			<th style="text-align:center;" colspan='2'>Confirmed Order</th>
			</tr>
			</thead>
				
			<tbody>
	
			<tr><td>ORD_NO</td>
				<td><div><input type='text' name='ord_no'></div></td></tr>
			<tr><td>ORD_STATUS</td>
				<td><div><input type='radio' name='ord_status' value=1 >已付款</div>
					<div><input type='radio' name='ord_status' value=0 checked>未付款</div></td></tr>
			<tr><td>PROD_NO</td>
				<td>
			
				<ul>		
					<li><input type="text" value="1"></li>
					<li><input type="text" value="2"></li>
					<li><input type="text" value="3"></li>
					<li><input type="text" value="4"></li>	
				</ul>
					
				</td></tr>
			<tr><td>Product Price</td>
				<td><div id='prodPrice'></div></td></tr>
				
				
			<tr><td>QUANTITY</td>
				<td><input type='number' name='quantity' id='quantity' min=1 value=1 ></td></tr>
			<tr><td>ORD_TOTAL</td>
				<td><div id='totalPrice'></div></td></tr>
			<tr><td>ORD_DATETIME</td>
				<td><div id='myDate'></div></td></tr>
		
			</tbody>
			<tfoot>
			<tr><td colspan='2'><div>
			<input type="hidden" name="action" value="AddOneOrder">
			<input type="button" class='submitBtn' value="Submit">
			<input type="reset" value="Reset">
			</div>
			</td></tr>
			</tfoot>
		
		</table>
		
	</form>
<script>
window.onload = function() {
		myprod();
		
	};

// 	$('.submitBtn')[0].addEventListener("click", mySubmit);
	document.getElementsByClassName('submitBtn') [0].addEventListener("click", mySubmit);
	var total,num;
	var myProd;
	var myDate;
function myprod(){
	//get from selected prod price
	myProdNo = document.getElementById("prod").value;
	console.log('select no - '+myProdNo);
	
	//put into prod price & set number default as 1
	document.getElementById("prodPrice").innerText = myProdNo;
	document.getElementById("quantity").value = 1;
	// set total 
	calTotal(1);
	// set date
	myDate = new Date().toISOString().slice(0, 19).replace('T', ' ');
	$('#myDate').text(myDate);
}	

$("#quantity").on('keyup mouseup', function () {
		var locNum = document.getElementById("quantity").value;
		calTotal(locNum);
});
function mySubmit(){
	$('form')[0].submit();
}
	

function calTotal(locNum){
	//calulate	
	total = myProdNo*locNum;
	$("#totalPrice").text(total);
}

</script>
</body>
</html>