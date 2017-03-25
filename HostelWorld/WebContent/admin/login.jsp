<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>经理登录</title>

	<!-- Stylesheets -->
	<link rel="stylesheet" href="../css/area.css">
	<link rel="stylesheet" href="../css/button.css">
	<link rel="stylesheet" href="../css/font.css">
	<link rel="stylesheet" href="../css/input.css">
	<link rel="stylesheet" href="../css/signup.css">
	
	
	
	
	
	<style>
        body{
            background-color: #E3A18B;
        }
        a {
            color: #fff;
            text-decoration: none;
        }
    </style>
    
    <script src="../js/jquery.min.js"></script>
    <script type="text/javascript">
    function login(){
		if(document.getElementById("password").value=="")
			alert("请输入密码");
		else if(document.getElementById("managerId").value=="")
			alert("请输入客栈编号");
		else{
			$.ajax({
				type:"POST",
				url:"/HostelWorld/LoginServlet",
				data:{
					type:"manager",
					id:document.getElementById("managerId").value,
					password:document.getElementById("password").value
				},
				datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
                success:function(data){
                    if(data=="nouser"){
                        alert("ID错误请重新输入");
                        document.getElementById("managerId").value="";
                        document.getElementById("password").value="";
                    }
                    else if(data=="wrong"){
                        alert("密码错误");
                        document.getElementById("password").value="";
                    } else {//跳转到经理首页
                    	window.location.href="manager.jsp"; 
                    }
                }
			});
		}
	}
	</script>
    
</head>

<%
	String login="";
	Cookie cookie = null;
    Cookie[] cookies = request.getCookies();

    if (null != cookies) {
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if (cookie.getName().equals("LoginCookie")) {
                login=cookie.getValue();
                break;
            }
        }
    }
%>

<body>
      
      <div style="font-family:Lucida Console;font-size: 2em; padding-top:10%">
        
        <span class="input input--hoshi input_center" >
            <input class="input__field input__field--hoshi" type="text" id="managerId"/>
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Manager ID</span>
            </label>
        </span>
        <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="password" id="password" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Password</span>
            </label>
        </span>
        <div onclick="login()" class="sim-button button10" style="float:right;right:35%"><span style="font-size:18px;color:#B20837">Login</span></div>    
   	 </div>
        
        <script src="../js/classie.js"></script>
		<script>
			(function() {
				if (!String.prototype.trim) {
					(function() {
						var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
						String.prototype.trim = function() {
							return this.replace(rtrim, '');
						};
					})();
				}

				[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
					if( inputEl.value.trim() !== '' ) {
						classie.add( inputEl.parentNode, 'input--filled' );
					}

					// events:
					inputEl.addEventListener( 'focus', onInputFocus );
					inputEl.addEventListener( 'blur', onInputBlur );
				} );

				function onInputFocus( ev ) {
					classie.add( ev.target.parentNode, 'input--filled' );
				}

				function onInputBlur( ev ) {
					if( ev.target.value.trim() === '' ) {
						classie.remove( ev.target.parentNode, 'input--filled' );
					}
				}
			})();
		</script>
</body>


</html>