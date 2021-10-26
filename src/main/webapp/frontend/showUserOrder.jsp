<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- customer session -->
<%@ page import="idv.customer.model.CustomerVO" %>
<!-- read order from product -->
<%@ page import="idv.ord.model.OrdVO" %>
<%@ page import="idv.ord_details.model.Ord_detailsVO" %>
<%@ page import="idv.product.model.ProductVO" %>
<!-- utility  -->
<%@ page import="java.util.List" %>


<jsp:useBean id="ord_Svc" scope="page" class="idv.ord.model.OrdService"/>
<jsp:useBean id="customer_Svc" scope="page" class="idv.customer.model.CustomerService"/>


<!DOCTYPE html>

<%
	CustomerVO customer = (CustomerVO)session.getAttribute("customer");
	if(customer==null){
		response.getOutputStream().print("Please Login First!!");
	}
%>
<html>
<head>
<title>Order List or Cart List</title>

</head>
<body>

	<H4>This is <span style="color:blue;">${customer.name }</span> order list - </H4>
	
	<table border="1">
		<thead>
		<c:forEach items="${ord_Svc.ordHeader}" var="header_name">
			<th><div style="color:blue;text-align:center;width:150px;">${header_name}</div></th>
		</c:forEach>
			<th>
		   <a class="btn btn-success btn-sm" href="#" style="color: orange">
           <b>檢視明細</b></a> 
        	</th>
		</thead>
		<tbody>
			<c:forEach items="${ord_Svc.getMyOrd(customer.no) }" var="ord">
			<tr>
			<td><div style="text-align:center;">${ord.ord_no }</div></td>
			<td style="text-align:center;">${customer_Svc.getOneCustomer(ord.customer_no).name }</td>
			<td style="text-align:center;">${(ord.ord_status==0)? 'UnDelivered':'Delivered' }</td>
			<td style="text-align:center;">${ord.receiver }</td>
			<td style="text-align:center;">${ord.rec_phone }</td>
			<td style="text-align:center;">${ord.rec_zip }</td>
			<td style="text-align:center;">${ord.rec_address }</td>
			<td style="text-align:center;">${ord.ord_total }</td>
			<td style="text-align:center;">${ord.ord_datetime }</td>
			<td> <FORM method="post" action="<%=request.getContextPath()%>/ord.do" id="myForm" name="myForm">
                    <button class="btn btn-success"><i class="fa fa-search-plus lg"></i> 訂單明細</button>
                    <input type="hidden" name="ord_no" value="${ord.ord_no}">
                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller(OrdServlet.java)-->
		            <input type="hidden" name="action" value="listDetails_ByOrdno">
	            </FORM>
			</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<%if (request.getAttribute("listOrderDetails_ByOrdno")!=null){%>
		<HR style="color: #5cb85c; background-color: #5cb85c; height: 1px;">
       <jsp:include page="listOrderDetails_ByOrdno.jsp" />
	<%} %>

</body>
</html>