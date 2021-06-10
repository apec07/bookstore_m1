<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<style>
	.linkbtn {
  font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;
	}
	
::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
  color: red;
  opacity: 1; /* Firefox */
}

</style>

<title>MoMo BookStore Login Page</title>
</head>
<body>
		<form action="${pageContext.request.contextPath}/user.do" method="post">
		<input type="hidden" name="action" value="login">
		<table border=1 align="center">
		<tr>
			<th colspan=3>Store Login Page</th>
		</tr>
		<tr>
			<td>UserName</td><td colspan=2><input type="text" name="name" class="name" placeholder="${errorMsgs.name_error}" value="${customerName}"></td>
		</tr>
		<tr>
			<td>Password</td><td colspan=2><input type="password" name="password" class="password" placeholder="${errorMsgs.pass_error}"></td>
		</tr>
		<tr>
			<td colspan=3 align="center">
			<button type="submit" value="Submit">Submit</button>&nbsp;&nbsp;
  			<button type="reset" value="Reset">Reset</button>&nbsp;&nbsp;
  			<a href="${pageContext.request.contextPath}/register.jsp" class="linkbtn">Register</a>
			</td>
		</tr>
  		</table>
		</form>
		
</body>
</html>