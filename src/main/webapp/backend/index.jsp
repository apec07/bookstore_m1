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
 String[] urls = new String[]{"/backend/product/showAllProd.jsp","/backend/product/showOneProd.jsp"};
 request.setAttribute("htmlchanged",urls);
%>
<title>Back-End Manager</title>
</head>
<body>
	<H1>Hi ${backend.name}</H1>
	
	<a href="${pageContext.request.contextPath}/backend/mem.do?action=logout">Logout</a>
	<HR>
	<!-- List / Add Switch button -->

	<select name="selectID" onchange="mySelect(this)">
		<option value="all">List All</option>
		<option value="one">Add One</option>
		<!-- how to change htmlchanged index　？？ 
			1. form to Servlet then back here 
			2. 2 pages loaded complete and switched by Ajax?      　
		-->
	</select> 
	
	<!--  EL Changed  -->
	<jsp:include page="${htmlchanged[0]}" flush="true" />
	
	<script type="text/javascript">
		function mySelect(selectObject){
			var value = selectObject.value;  
			console.log('select - '+value);
		
		}	
	</script>
</body>
</html>