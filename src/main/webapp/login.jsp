<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>

<title>MoMo BookStore Login Page</title>
</head>
<body>
		<form action="${pageContext.request.contextPath}/user.do" method="post">
			<input type="text" name="name" value="" placeholder="username"><BR>
			<input type="text" name="password" value="" placeholder="password"><BR>
			<input type="hidden" name="action" value="login">
			<button type="submit" value="Submit">Submit</button>&nbsp;&nbsp;
  			<button type="reset" value="Reset">Reset</button>
		</form>
		
</body>
</html>