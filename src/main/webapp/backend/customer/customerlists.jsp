<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<title>Customer List</title>
</head>
<body>
<h2>Here is Customer list as below (AJAX)</h2>
<form id="filterSelect" action="${pageContext.request.contextPath}/backend/user.do" method="post">
<input type="hidden" name="action" value="listLike">
<input type="text" name="filter" onchange="myfilter(this)">
</form>
<table border=1>
	<thead>
		<th>no</th>
		<th>name</th>
		<th>password</th>
		<th>email</th>
	</thead>
	<tbody>
		
   		<c:forEach items="${customerSvc.allCustomer}" var="customer">
		<tr>
			<td>${customer.no}</td>
			<td>${customer.name}</td>
			<td>${customer.password}</td>
			<td>${customer.email}</td>
		</tr>
		</c:forEach>
	
		
	</tbody>
</table>

</body>
<script>
function myfilter(inputObject){
	var inputStr = inputObject.value;  
	console.log('select - '+inputStr);
	$.ajax({
	    type:"POST",
	    url: "${pageContext.request.contextPath}/backend/user.do",
	    data: {'action' : 'listLike','filter': inputStr},
	    success:function (result) {
	        if("false"==result){
	        	
	        }else if("true"==result){
	            console.log("duplicated mail - "+$("input.name").val());
	        }else{
	        	console.log("custom undefined");
	        }
	    },
	    error:function (err) {
	    	$("div.mailMsg").text(""+err);
	    }
	});
}	

</script>
</html>