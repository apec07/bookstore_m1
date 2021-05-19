<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="idv.member.model.MemberVO" %>

<!DOCTYPE html>
<html>
<head>

<title>MoMo BookStore Register Page</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user.do" method="post">
	<table border=1 align="center">
		<tr>
			<th colspan=3>Store Account Register</th>
		</tr>
		<tr>
			<td>UserName</td><td colspan=2><input type="text" name="name" class="name"><span class="message">${errorMsgs.customer_name}</span></td>
		</tr>
		
		<tr>
			<td>Password</td><td colspan=2><input type="password" name="password" class="password"></td>
		</tr>
		
		<tr>
			<td>Re-type</td><td colspan=2><input type="password" class="password" onChange="check()"></td>
		</tr>
		
		<tr>
			<td>Email</td><td colspan=2><input type="email" name="email" class="email"><span class="message">${errorMsgs.customer_email}</span></td>
		</tr>	
		<tr>
		<td colspan=3>
		<div style="text-align:center">
		<input type="hidden" name="action" value="register">
		<button type="submit" value="Submit">Submit</button>
		<button type="reset" value="Reset">Reset</button>
		</div>
		</td>
		
		</tr>
	</table>
	</form>
	
 <script type="text/javascript">
 	function check(){
 		
 	}
</script>
</body>
</html>