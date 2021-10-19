<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="idv.member.model.MemberVO" %>
<jsp:useBean id="prod_categorySvc" scope="page" class="idv.prod_category.model.Prod_categoryService" />
<jsp:useBean id="prod_Svc" scope="page" class="idv.product.model.ProductService" />

<!DOCTYPE html>


<html>
<head>
<%
// Check Fron-end user
 
%>
</head>
<body>


<table border=1 style="vertical-align:middle;">
	<thead>
		<c:forEach items="${prod_Svc.prodHeader}" var="header_name">
		<th>${header_name}</th>
		</c:forEach>
	</thead>
	<tbody>
		<c:forEach items="${prod_Svc.allProd}" var="prod">
			<%--display online product for user --%>
			<c:if test="${prod.prod_status==1}">
		<tr>
		<td class="prod_no">${prod.product_no}</td>
		<td><c:forEach var="prodcat" items="${prod_categorySvc.all}">
                    <c:if test="${prod.category_no==prodcat.category_no}">
	                    ${prodcat.category_no}【${prodcat.category_name} - ${prodcat.category_descr}】
                    </c:if>
            </c:forEach></td>
		<td class="prod_name">${prod.prod_name}</td>
		<td class="prod_price">${prod.prod_price}</td>
		<td>${prod.prod_introduce}</td>
		<td>${prod.prod_stock}</td>
		<td>${(prod.prod_status==0)? 'Offline':'Online' }</td>
		<td><img width='100' src="${pageContext.request.contextPath}/reader/DBGifReader?product_no=${prod.product_no}"/></td>
		<td>
		<!-- Cart -->
		<form method="post" action="${pageContext.request.contextPath}/cart.do" >
		<input type="hidden" name="prod_no" value="${prod.product_no}"/>
		<input type="hidden" name="prod_name" value="${prod.prod_name}"/>
		<input type="hidden" name="prod_price" value="${prod.prod_price}"/>
		<input type="hidden" name="action" value="cart"/>
		<input type="number" name="quantity" value="1" min="1"/>
		<input type="submit" value="Add to Cart"/>
		</form>
		
		</td>
		</tr>
			</c:if>
		</c:forEach>
	</tbody>

</table>

<script>
		var prod_nos = document.getElementsByClassName("prod_no");
		var prod_names = document.getElementsByClassName("prod_name");
		var prod_prices = document.getElementsByClassName("prod_price");
		
		
</script>

</body>
</html>
