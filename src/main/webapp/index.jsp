<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="idv.customer.model.CustomerVO" %>

<!DOCTYPE html>
<!-- Redirect to login page -->
<%
	CustomerVO customer = (CustomerVO)session.getAttribute("customer");
	if(customer==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
%>
<html>
<head>
<title>MoMo Book Store HomePage</title>

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

</head>
<body>
	<h2>Hi, <strong>${customer.name}</strong></h2>
	<a href="${pageContext.request.contextPath}/user.do?action=logout" >Logout</a>
	<BR><BR>
	<!-- Tab links -->
	<div class="tab">
  	<button class="tablinks" onclick="openCity(event, 'prod')" id="defaultOpen">Product List</button>
  	<button class="tablinks" onclick="openCity(event, 'cart')">Cart List</button>
 	 <button class="tablinks" onclick="openCity(event, 'favor')">My Favorite</button>
	</div>
	

<!-- Tab content -->
<div id="prod" class="tabcontent">
  <h3>Product List</h3>
	<jsp:include page="/frontend/showAllProd.jsp" flush="true" />
</div>

<div id="cart" class="tabcontent">
  <h3>Cart List</h3>
  <p>TBD - Linked with user name</p>
</div>

<div id="favor" class="tabcontent">
  <h3>My Favorite</h3>
  <p>TBD - Linked with user name.</p>
</div> 


<script>
function openCity(evt, categ) {
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
</script> 
</body>
</html>