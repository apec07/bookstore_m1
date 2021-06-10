/**
 *  this is memo for js
 */
// 按下Enter 會呼叫 submit 按鈕的 click
$("body").keydown(function() {
       if (event.keyCode == "13") {// keyCode=13 is Enter
           $('#submit').click();
        }
});
//JQuery Ready
$(function(){
    //遍历获取的input元素对象数组，绑定click事件
    var len = $("input[type='file']").length;
    for(var i = 0; i < len; i++){
        $("input[type='file']").eq(i).click(function(){
            $(this).next().val("");
            $(this).next().hide();
            $(this).css("width","200px");
        })
    }
}
//JS Ready
$(document).ready(function(){
	  $("input").focus(function(){
	    $("input").css("background-color","#FFFFCC");
	  });
	  $("input").blur(function(){
	    $("input").css("background-color","#D6D6FF");
	  });
});

// define that all input - css (focus/blur)
function loadinputCss(){
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
	
}
// 按鈕送出 Submit 
$('#btnsubmit').click(function () {
	 alert("按鈕送出");
//	 document.getElementById("login").submit(); -> JS 
//	 $('#login').submit();                      -> JQuery                     
});

// document btn disabled
document.getElementById("myBtn").disabled = true;