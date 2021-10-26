<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="productSvc" scope="page" class="idv.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
<title>�q��޲z-�q�����- listOrderDetails_ByOrdno.jsp</title>

</head>
<body>

<table style="width:65%; text-align:left;">
	<tr><td><h3><i class="fa fa-cog fa-spin fa-2x"></i>�q�����</h3></td></tr>
<!-- 	<tr><td><h4>listOrderDetails_ByOrdno.jsp</h4></td></tr> -->
</table>

<table class="orderDetail_Table" border="1" >
	<tr height="35px">
		<th>�ӫ~�Ϥ�</th>
		<th>�q��s��</th>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ƶq</th>
		<th>���</th>
        <th>���B</th>
	</tr>
	<c:forEach var="ord_detailsVO" items="${listOrderDetails_ByOrdno}" >
		<tr align='center' valign='middle'>
			<td><img src="<%=request.getContextPath()%>/reader/DBGifReader?product_no=${ord_detailsVO.product_no}" class="img-rounded2"></td>
			<td>${ord_detailsVO.ord_no}</td>
			<td>${ord_detailsVO.product_no}</td>
			<td>${productSvc.getOneProd(ord_detailsVO.product_no).prod_name}</td>
			<td>${ord_detailsVO.quantity}</td>
			<td>${ord_detailsVO.prod_price}</td>
			<td>${ord_detailsVO.prod_price*ord_detailsVO.quantity}</td>
	    </tr>
	</c:forEach>
</table>
</body>
</html>