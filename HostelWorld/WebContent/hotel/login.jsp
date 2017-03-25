<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>客栈登录</title>

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
		else if(document.getElementById("hotelid").value=="")
			alert("请输入客栈编号");
		else{
			$.ajax({
				type:"POST",
				url:"/HostelWorld/HotelLoginServlet",
				data:{
					id:document.getElementById("hotelid").value,
					password:document.getElementById("password").value
				},
				datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
                success:function(data){
                    if(data=="nohotel"){
                        alert("客栈编号错误请重新输入");
                        document.getElementById("hotelid").value="";
                        document.getElementById("password").value="";
                    }
                    else if(data=="wrong"){
                        alert("密码错误");
                        document.getElementById("password").value="";
                    }
                    else{
                        if(data=="processed")
                            alert("您的开店申请正在等待经理审核!");
                        else if(data=="approved")
                            alert("您的开店申请已通过经理审核!");
                        else if(data=="disapproved")
                            alert("您的开店申请被经理拒了!");
                        window.location.href="hotelinfo.jsp"; 
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
        // Look through all the cookies and see if the
        // cookie with the login info is there.
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

    <!--  <div style="width:100%; height:auto;">
        <div style="position:relative; float:left; margin:20px 10% auto;">
            <img src="../images/desserts.jpeg" style="height:600px">
        </div>
        
        <div style="position:relative; float:left; width:45%; height:400px;margin:130px 0 auto; padding:0px;font-family:Cooper Black;">
            <span class="input input--haruki">
                <input class="input__field input__field--haruki" type="text" id="userid" value=<%=login %> />
                <label class="input__label input__label--haruki" >
                    <span class="input__label-content input__label-content--haruki" style="font-size:16px;">User ID</span>
                </label>
            </span>
            <span class="input input--haruki">
                <input  type="password"  class="input__field input__field--haruki" type="text" id="password" />
                <label class="input__label input__label--haruki" >
                    <span class="input__label-content input__label-content--haruki" style="font-size:16px;">password</span>
                </label>
            </span>
            
            <a href="#" style="position:relative; top:210px; left:-270px">忘记密码？</a>
            
            <div onclick="login()" class="sim-button button2" data-text="Login"  style=" margin-left:255px"><span>Login</span></div>
        </div>
   </div>
      -->  
      
      <div style="font-family:Lucida Console;font-size: 2em; padding-top:10%">
        
        <span class="input input--hoshi input_center" >
            <input class="input__field input__field--hoshi" type="text" id="hotelid"/>
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Hotel ID</span>
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
				// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
				if (!String.prototype.trim) {
					(function() {
						// Make sure we trim BOM and NBSP
						var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
						String.prototype.trim = function() {
							return this.replace(rtrim, '');
						};
					})();
				}

				[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
					// in case the input is already filled..
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