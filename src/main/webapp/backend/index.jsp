<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="idv.member.model.MemberVO" %>
<%! int i=0; %>
<!DOCTYPE html>
<html>
<head>
<% 
  MemberVO member = (MemberVO)session.getAttribute("backend");
 if(member==null){
	 response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
 }
 String p1 = (String)request.getAttribute("usePage");
 if(p1==null){
	 request.setAttribute("usePage", "/backend/product/showAllProd.jsp");	 
 }
 
%>
<title>Back-End Manager</title>
</head>
<body>
	<H1>Hi ${backend.name}</H1>
	
	<a href="${pageContext.request.contextPath}/backend/mem.do?action=logout">Logout</a>
	<HR>
	<!-- List / Add Switch button -->
	<form id="formSelect" action="${pageContext.request.contextPath}/backend/prod.do" method="post">
	<input type="hidden" name="action" value="selectPage"/>
	<select name="usePage" onchange="mySelect(this)">
		<option value="all" ${(usePage=='/backend/product/showAllProd.jsp')? 'selected':'' }>List All</option>
		<option value="one"	${(usePage=='/backend/product/showOneProd.jsp')? 'selected':'' }>Add One</option>
		<!-- how to change htmlchanged index　？？ 
			1. form to Servlet then back here 
			2. 2 pages loaded complete and switched by Ajax?      　
		-->
	</select> 
	</form>
	<!--  EL Changed  -->
	<jsp:include page="${usePage}" flush="true" />
	
	<script type="text/javascript">
		function mySelect(selectObject){
			var value = selectObject.value;  
			console.log('select - '+value);
			document.getElementById('formSelect').submit()
		}	
	</script>
</body>
</html>