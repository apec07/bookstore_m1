<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Table Filter Test</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<link rel="stylesheet" href="mystyle.css">
</head>
<body>

<table>
    <thead>
       <tr><input type="text" class="platform_filter"/><th>Num Heading</th></tr>
        </thead>
    <tbody>
       <tr><td class="name">ABC</td><td class="number">12</td></tr>
       <tr><td class="name">DEF</td><td class="number">23</td></tr>
       <tr><td class="name">ABC</td><td class="number">34</td></tr>
       <tr><td class="name">apolo</td><td class="number">45</td></tr>
   </tbody>
</table>


<script type="text/javascript">
	$('.platform_filter').keyup(function(){
	 var val=$(this).val();    
	        $('table tbody tr').hide();
	         var trs=$('table tbody tr').filter(function(d){
	         return $(this).text().toLowerCase().indexOf(val)!=-1;
	         });
	         console.log(trs);
	         trs.show();   
	});

</script>
</body>
</html>