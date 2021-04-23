<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="prod_categorySvc" scope="page" class="idv.prod_category.model.Prod_categoryService" />

<!DOCTYPE html>


<html>
<body>
<h2>Here is Prod_category List as below </h2>

<table border=1>
	<thead>
		<c:forEach items="${prod_categorySvc.all}" var="prod">
		<tr>
		<th>${prod.category_name}</th><th>${prod.category_descr}</th>
		</tr>
		</c:forEach>
	</thead>
	<tbody>
		<tr>
		
		</tr>
	</tbody>

</table>

</body>
</html>
