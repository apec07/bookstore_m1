<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="idv.member.model.MemberVO" %>

<!DOCTYPE html>
<html>
<head>
<style>
 /* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons that are used to open the tab content */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
} 
</style>
<% 
  MemberVO member = (MemberVO)session.getAttribute("backend");
 if(member==null){
	 response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
 }
 String p1 = (String)request.getAttribute("usePage");
 if(p1==null){
	 request.setAttribute("usePage", "/backend/product/showAllProd.jsp");	 
 }
  
  if(session.getAttribute("isDelete")!=null){
	  boolean isDelete = (boolean)session.getAttribute("isDelete");
	  if(isDelete){
		log("delete!");
		 %>
		 <script type="text/javascript">
	 	 alert('delete');
	  	</script>
	  	<%
	  }else{
		log("unable deleted");
		 %>
		 <script type="text/javascript">
	 	 alert('unable deleted');
	  </script>
	   <%
	  }
	  // removed if notice
	  session.removeAttribute("isDelete");
  }
 
%>
<title>Back-End Manager</title>
</head>

<body>
	<H1>Hi ${backend.name}</H1>
	
	<a href="${pageContext.request.contextPath}/backend/mem.do?action=logout">Logout</a>
	<HR>
	<!-- Tab links -->
	<div class="tab">
  	<button class="tablinks" onclick="openManage(event, 'prod')" id="defaultOpen">Product</button>
  	<button class="tablinks" onclick="openManage(event, 'customer')">Customer</button>
 	<button class="tablinks" onclick="openManage(event, 'order')">Order</button>
	</div>
	

	<!-- Tab content -->
	<div id="prod" class="tabcontent">
  	<h3>Product Management</h3>
	<p>TBD - Linked with Product</p>
	</div>

	<div id="customer" class="tabcontent">
  	<h3>Customer Management</h3>
  	<p>TBD - Linked with Customer</p>
	</div>

	<div id="order" class="tabcontent">
 	 <h3>Order List Management</h3>
  	<p>TBD - Linked with Order</p>
	</div> 
	
	<HR>
	<!-- List / Add Switch button -->
	<form id="formSelect" action="${pageContext.request.contextPath}/backend/prod.do" method="post">
	<input type="hidden" name="action" value="selectPage"/>
	<select name="usePage" onchange="mySelect(this)">
		<option value="all" ${(usePage=='/backend/product/showAllProd.jsp')? 'selected':'' }>List Prod</option>
		<option value="one"	${(usePage=='/backend/product/showOneProd.jsp')? 'selected':'' }>Add Prod</option>
		<!-- how to change htmlchanged index　？？ 
			1. form to Servlet then back here 
			2. 2 pages loaded complete and switched by Ajax?      　
		-->
	</select> 
	</form>
	<!--  EL Changed  -->
	<jsp:include page="${usePage}" flush="true" />
	
	<script type="text/javascript">
	//Tablink
	function openManage(evt, categ) {
	  // Declare all variables
	  var i, tabcontent, tablinks;

	  // Get all elements with class="tabcontent" and hide them
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }

	  // Get all elements with class="tablinks" and remove the class "active"
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }

	  // Show the current tab, and add an "active" class to the button that opened the tab
	  document.getElementById(categ).style.display = "block";
	  evt.currentTarget.className += " active";
	} 
	// Get the element with id="defaultOpen" and click on it
		document.getElementById("defaultOpen").click();
	//Dropdwon list select
		function mySelect(selectObject){
			var value = selectObject.value;  
			console.log('select - '+value);
			document.getElementById('formSelect').submit()
		}	
	</script>
</body>
</html>