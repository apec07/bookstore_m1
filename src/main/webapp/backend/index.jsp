<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="idv.member.model.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<% 
  MemberVO member = (MemberVO)session.getAttribute("backend");
 if(member==null){
	 response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
 }
%>
<title>Back-End Manager</title>
</head>
<body>
	<H1>Hi ${backend.name}</H1>
	
	<a href="${pageContext.request.contextPath}/backend/mem.do?action=logout">Logout</a>
</body>
</html>