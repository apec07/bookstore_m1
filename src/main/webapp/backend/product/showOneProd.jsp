<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="idv.prod_category.model.Prod_categoryVO"%>
<%@ page import="idv.product.model.ProductVO"%>
<%@ page import="idv.member.model.MemberVO"%>
<jsp:useBean id="prod_categorySvc" scope="page"
	class="idv.prod_category.model.Prod_categoryService" />

<%--
	private Integer product_no;  //PK
	private Integer category_no; //FK
	private String prod_name;
	private Integer prod_price;
	private String prod_introduce;
	private Integer prod_stock;
	private Integer prod_status; 
	private byte[] prod_pic;	
 --%>

<!DOCTYPE html>
<html>
<head>

<%
	// Check Login
	MemberVO member = (MemberVO) session.getAttribute("backend");
	if (member == null) {
		response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
	}
	// get product for Dispatcher 
	ProductVO product = (ProductVO) request.getAttribute("oneProd");
	session.setAttribute("oneProd", product);
%>
</head>
<body>
	<!-- judge if add or update -->
	<c:if test="${oneProd !=null }" var="isUpdate">
   	<H2><c:out value="Update Page"/></H2>
	</c:if>
	<c:if test="${oneProd ==null }" var="isAdd">
	<H2><c:out value="Add Page"/></H2>
	</c:if>
	

	<form method="post"	action="${pageContext.request.contextPath}/backend/prod.do" enctype="multipart/form-data">
	
		<c:if test="${oneProd !=null }" var="isUpdate">
   		<input type="hidden" name="action" value="updateOne" /> 
		</c:if>
		<c:if test="${oneProd ==null }" var="isAdd">
		<input type="hidden" name="action" value="AddOne" /> 
		</c:if>
		
		<c:if test="${oneProd !=null }" var="isUpdate">
		<input type="hidden" name="prod_no" value="${oneProd.product_no}" />
		</c:if>
		
		<table border=1>
			<tr>
				<td>Product Name:</td>
				<td><input type="text" name="prod_name" class="input" value="${oneProd.prod_name}"></td>
			</tr>
			<tr>
				<td>Product Introduce :</td>
				<td>
				<textarea name="prod_introduce" class="input" cols="50" rows="4">
				${oneProd.prod_introduce}</textarea></td>
			</tr>
			<tr>
				<td>Product Price :</td>
				<td><input type="number" name="prod_price" class="input" value="${oneProd.prod_price}"></td>
			</tr>
			<tr>
				<td>Product Stock :</td>
				<td><input type="number" name="prod_stock" class="input" value="${oneProd.prod_stock}"></td>
			</tr>
			<tr>
				<td>Product Status :</td>
				<td>
				<!-- offline - 0 -->
				<input type="radio" name="prod_status" class="input" value="0" ${(oneProd.prod_status==0)? 'checked':'' }>Offline&nbsp;
				<!-- online - 1 -->
				<input type="radio" name="prod_status" class="input" value="1" ${(oneProd.prod_status==1)? 'checked':'' }>Online
				</td>
			</tr>
			<tr>
				<td>Product Category :</td>
				<td><select name="category_no">
						<c:forEach items="${prod_categorySvc.all}" var="cat">
							<option value="${cat.category_no}"
								${(oneProd.category_no==cat.category_no)? 'selected':'' }>${cat.category_name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Product Picture :</td>
				<td><input type="file" class="input" name="prod_pic"id="prod_pic" onchange="previewImage()" /> 
				<img id="blob_holder" width='300' src="${pageContext.request.contextPath}/reader/DBGifReader?product_no=${oneProd.product_no}"></td>
			</tr>
		</table>

		<br> 
		<c:if test="${oneProd !=null }" var="isUpdate">
   		<input type="submit" value="update" /> 
		</c:if>
		<c:if test="${oneProd ==null }" var="isAdd">
		<input type="submit" value="Add" /> 
		</c:if>
	
		<a href="${pageContext.request.contextPath}/backend/">Back	to Home</a>
	</form>

	<script type="text/javascript">
		function previewImage() {
			var prod_pic = document.getElementById("prod_pic");

			// 確定選取了一個二進位檔案，而非其他格式。
			if (prod_pic.files.length != 0
					&& prod_pic.files[0].type.match(/image.*/)) {
				var reader = new FileReader();
				reader.onload = function(e) {
					var theImg = document.getElementById("blob_holder");
					theImg.src = e.target.result;
				};
				reader.onerror = function(e) {
					alert("例外狀況，無法讀取二進位檔");
				};
				// 讀取二進位檔案。
				reader.readAsDataURL(prod_pic.files[0]);
			} else {
				alert("請選取一個二進位檔");
			}
		}
	</script>
</body>
</html>