<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="prod_categorySvc" scope="page" class="idv.prod_category.model.Prod_categoryService" />
<jsp:useBean id="prod_Svc" scope="page" class="idv.product.model.ProductService" />

<!DOCTYPE html>


<html>
<body>
<h2>Here is Prod_category List as below </h2>

<table border=1>
	<thead>
		<c:forEach items="${prod_categorySvc.all}" var="prodcat">
		<tr>
		<th>${prodcat.category_name}</th><th>${prodcat.category_descr}</th>
		</tr>
		</c:forEach>
	</thead>
	<tbody>
		<c:forEach items="${prod_Svc.allProd}" var="prod">
		<tr>
		<td>${prod.product_no}</td>
		<td>${prod.category_no}</td>
		<td>${prod.prod_name}</td>
		<td>${prod.prod_price}</td>
		<td>${prod.prod_introduce}</td>
		<td>${prod.prod_stock}</td>
		<td>${prod.prod_status}</td>
		<td><img src="${pageContext.request.contextPath}/resources/NoData/nopic.jpg"/></td>
		</tr>
		</c:forEach>
	</tbody>

</table>

</body>
</html>
