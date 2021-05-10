<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="idv.member.model.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="memSvc" scope="page" class="idv.member.model.MemberService" />	
<title>Back-End Login Page</title>
</head>
<body>
<H1>Back-End Login Select Page</H1>

<form method="post" action="mem.do">
<select name="mem_no">
<c:forEach var="memVO" items="${memSvc.allMember}">
	<!-- default selected ? -->
	<option value="${memVO.no}" ${(param.no==memVO.no)? 'selected':'' } >${memVO.name}</option>

</c:forEach>
</select>
<%-- 		<input type="hidden" name="memNo" value="${memVO.no}"> --%>
		<input type="hidden" name="action" value="backend_login">
		<input type="submit" value="login">
</form>

</body>
</html>