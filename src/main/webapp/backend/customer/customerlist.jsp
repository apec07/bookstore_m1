<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="idv.customer.model.CustomerVO" %>
<jsp:useBean id="customerSvc" scope="page" class="idv.customer.model.CustomerService" />
<%String like; %>

<!DOCTYPE html>
<html>
<head>
<title>Customer List</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>

<h2>Here is Customer list as below </h2>
<form id="filterSelect" action="${pageContext.request.contextPath}/backend/user.do" method="post">
<input type="hidden" name="action" value="listLike">
<input type="text" name="filter" onKeyUp="myfilter(this)">
</form>
<table border=1 id="customerGrid">
	<thead>
		<th>no</th>
		<th>name</th>
		<th>password</th>
		<th>email</th>
	</thead>
	<tbody>
		<!-- judge if list all or some -->
		<c:if test="${isSome ==null }" var="isAll">
   		<c:forEach items="${customerSvc.allCustomer}" var="customer">
		<tr>
			<td class="no">${customer.no}</td>
			<td class="name">${customer.name}</td>
			<td class="pass">${customer.password}</td>
			<td class="email">${customer.email}</td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${isSome !=null }" var="isSome">
		<% like = (String)request.getAttribute("inputStr"); %>
		<c:forEach items="<%=customerSvc.getSomeCustomer(like) %>" var="customer">
		<tr>
			<td>${customer.no}</td>
			<td>${customer.name}</td>
			<td>${customer.password}</td>
			<td>${customer.email}</td>
		</tr>
		</c:forEach>
		</c:if>
	
		
	</tbody>
</table>
<script type="text/javascript">

		var myTableArray = [];
		$("table#customerGrid tr").each(function(){
			var arrayOfThisRow=[];
			var tableData = $(this).find('td');
			if(tableData.length >0 ){
				tableData.each(function(){arrayOfThisRow.push($(this).text());});
				myTableArray.push(arrayOfThisRow);
			}
		});
		console.log(myTableArray);


//wrong code - retrieve from backend (db)
	function myfilter_drop(inputObject){
		var value = inputObject.value;  
		console.log('select - '+value);
		document.getElementById('filterSelect').submit()
	}	
	
// use javascript to filter all data
	function myfilter(inputObject){
		var value = inputObject.value;  
		console.log('select - '+value);
		// delete contents (tbody) 
		 $('tbody').empty();
		// create row specific data - but how to filter?
		var names =[];
		for(i=0;i<myTableArray.length;i++){
			names.push(myTableArray[i][1]);
		}
		console.log('input []- '+names);
		/**
		 * 陣列透過搜尋條件（查詢）過濾物件
		 */
		function filterItems(query) {
		  return names.filter(function(el) {
		      return el.toLowerCase().indexOf(query.toLowerCase()) > -1;
		  })
		}
		console.log('match []-'+filterItems(value));
		createTable([['aa','bb'],['ccc','ddd']]); 	
//      	createTable([["row 1, cell 1", "row 1, cell 2"], ["row 2, cell 1", "row 2, cell 2"]]); 	
	}	
	

	
// 	var table = document.createElement('table');
// 	table.setAttribute('border','1');
// 要給二微陣列 - tableData 
	function createTable(tableData) {
		
// 		var tableBody = $(this).find('tbody');
// 		var tableBody = document.getElementsByTagName('tbody');
		  tableData.forEach(function(rowData) {
		    var row = document.createElement('tr');

		    rowData.forEach(function(cellData) {
		      var cell = document.createElement('td');
		      cell.appendChild(document.createTextNode(cellData));
		      row.appendChild(cell);
		    });

		    $("tbody").append(row);

		  });

		}

		

	 function hideTd(){
		var datas = document.getElementsByTagName('td');
		for(i=0;i<datas.length;i++){
			console.log(datas[i].innerHTML);
			datas[i].style.display = 'none';	
		}
		console.log("hide!");
	 }
</script>
</body>
</html>