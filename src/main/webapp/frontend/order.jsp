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
	<form action="url" method="post">
	<ul>
		<li>
			<select name="PRODUCT_NO" id='prod' onchange="myprod()">
				<option>1</option>
				<option>2</option>
				<option>3</option>
			</select>	
		</li>
		<li class="lv1"><input type="text" value="1"></li>
		<li><input type="text" value="2"></li>
		<li><input type="text" value="3"></li>
		<li><input type="text" value="4"></li>
	
	</ul>
	<input type='submit'>
	<input type='reset'>
	</form>
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