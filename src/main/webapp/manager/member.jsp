<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!-- 
	確認後台權限，使用協同，以下要
	1. 移植到  xml
	2. 或使用  jsp include file 
	方便管理
-->
<%
    Object permission = session.getAttribute("momo-backend");
	if(permission==null){
// 		request.getD
		String url = request.getServletPath();
		request.setAttribute("memberURL-backend",url);	
%>		
<!-- 轉送登入 --> 
<jsp:forward page="/manager/login.jsp"></jsp:forward>
<%
	}
%>


<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<h1>Test URL for Dispatcher</h1>
	<p><b><%=request.getAttribute("memberURL-backend") %></b></p>

</body>
</html>