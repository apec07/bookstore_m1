<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="idv.member.model.MemberVO" %>

<!DOCTYPE html>
<html>
<head>
<style>

</style>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<title>MoMo BookStore Register Page</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user.do" method="post" id="login">
	<table border=1 align="center">
		<tr>
			<th colspan=3>Store Account Register</th>
		</tr>
		<tr>
			<td>UserName</td><td colspan=2>
			<div ><input type="text" name="name" class="name" title="username" onchange="checkUser()" ><span class="namemsg"></span></div>
			<div class="nameMsg" style="color:red;"></div>			
			</td>
		</tr>
		
		<tr>
			<td>Password</td><td colspan=2>
			<div><input type="password" name="password" class="password" title="password" id="pass"></div>
			<div class="message">${errorMsgs.customer_password}</div>
			</td>
		</tr>
		
		<tr>
			<td>Re-type</td><td colspan=2>
			<div><input type="password" class="password" onChange="checkpass()" title="re-password" id="re_pass"></div>
			<div class="message"></div>
			</td>
		</tr>
		
		<tr>
			<td>Email</td><td colspan=2>
			<div><input type="email" name="email" class="email" title="Email"></div>
			<div class="message">${errorMsgs.customer_email}</div></td>
		</tr>	
		<tr>
		<td colspan=3>
		<div style="text-align:center">
		<input type="hidden" name="action" value="register">
		<button type="button" value="submit" id="btnsubmit" >Register</button>
		<button type="reset" value="Reset" id="reset" onclick="reset1()">Reset</button>
		</div>
		</td>
		
		</tr>
	</table>
	</form>
	
 <script type="text/javascript">
 	 var isExist = false;
	 $('#btnsubmit').click(function () {
		//Block if blank input
		var myform = document.getElementById("login");
		console.log("myform length - "+myform.length);
		for ( var i = 0; i < myform.length; i++) {
			console.log(myform.elements[i].value);
		   	if (myform.elements[i].value == "") {
		    	alert(myform.elements[i].title + "不能為空");
			    myform.elements[i].focus();
		    return;
   			}
  		}
		//submit form
		 $('#login').submit();
	 });
	 $(document).ready(function(){
		 $("input").css("background-color","#D6D6FF");
		   var x = document.getElementsByTagName("input");
		   var len = $("input").length;
		   console.log("len - "+len);
		   for(var i = 0; i < len; i++){
			   console.log(x[i]);
		   	 $(x[i]).focus(function(){
		    	$(this).css("background-color","#FFFFCC");
		 	 });
		  	$(x[i]).blur(function(){
		    	$(this).css("background-color","#D6D6FF");
		  	});
		  }
    });
	 	 
	function createUserImg(isExist){
		 var img = document.createElement('img');
		 console.log("createUserImg function call");
		 if(isExist){
			 console.log("true");
			 img.src = '${pageContext.request.contextPath}/resources/icons/remove.svg';
			 img.width='20';
		 }else{
			 console.log("false");
			 img.src = '${pageContext.request.contextPath}/resources/icons/check.svg';
			 img.width='20';
		 }
		 //make sure that img has not been added before!
		 $("span.namemsg").find("img").remove();
		 //add noticed img icon (V / X)
		 $("span.namemsg").append(img);
		
	}
	
	 
	function checkUser(){
		var userName=$("input[name='name']").val();
		if(userName.length!==0 && userName.trim().length===0){
			alert("username not allow white space");
			return;
		}
		if(userName.length===0){
			//wo type anything
			return;
		}
		console.log("user name is "+userName);
	    $.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/user.do",
            data:$('#login').serialize(),  //直接傳表單裡的資料
            success:function (result) {
                if("false"==result){
                	$("div.nameMsg").text("");
                	createUserImg(false);
                    alert(userName+"-名稱未使用");
                }else if("true"==result){
                    alert(userName+"-名稱已使用");
                	createUserImg(true);
                    console.log("duplicated name - "+$("input.name").val());
//                     console.log("msg "+$("div.nameMsg").text());
					$("div.nameMsg").text(userName +" has been used");  
//                     $("input.name").val("");  //clean input text
//                     $("input.name").focus();  //focus on input text
                }else{
                	console.log("no responsed from back-end");
                }
            },
            error:function (err) {
            	$("div.nameMsg").text("");
                alert("系統錯誤-register.jsp-ajax");
            }
        });
	} 
	function reset1(){
		console.log("reset status");
		document.getElementById("submit").disabled = false;
	}
 	function checkpass(){
 		var re_pass = document.getElementById("re_pass").value;
 		var pass = document.getElementById("pass").value;
 		if(pass!==re_pass){
 			document.getElementById("submit").disabled = true;
 			console.log("password misatched!");
 			return;
 		}
 	}
</script>
</body>
</html>