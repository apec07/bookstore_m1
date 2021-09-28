<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>

	li{
		border:1px #ccc solid;
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
		<li><input type="text" value="1"></li>
		<li><input type="text" value="2"></li>
		<li><input type="text" value="3"></li>
		<li><input type="text" value="4"></li>
	
	</ul>
	<input type='submit'>
	<input type='reset'>
	</form>
</body>
</html>