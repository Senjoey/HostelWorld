<%@page import="hostelworld.logic.impl.HotelManagerImpl"%>
<%@page import="hostelworld.logic.HotelManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>客栈注册</title>

	<!-- Stylesheets -->
	<link rel="stylesheet" href="../css/area.css">
	<link rel="stylesheet" href="../css/button.css">
	<link rel="stylesheet" href="../css/font.css">
	<link rel="stylesheet" href="../css/input.css">
	<link rel="stylesheet" href="../css/signup.css">
	
	<style>
        body{
			background-color: #F1D0C5;
		}
       
		a {
			color: #fff;
			text-decoration: none;
		}
       
    </style>
    <script src="../js/jquery.min.js"></script>
    <script>
    function register(){
    	if(document.getElementById("password").value=="")
    			alert("请填写密码");
    	else if(document.getElementById("card").value=="")
    			alert("请填写收款卡号");
    	else if(document.getElementById("password").value!=document.getElementById("password2").value)
    			alert("两次密码输入不一致");
    	else{
             $.ajax({
                 //提交数据的类型 POST GET
                 type:"POST",
                 //提交的网址
                 url:"/HostelWorld/HotelRegisterServlet",
                 //提交的数据
                 data:{
                	   password: document.getElementById("password").value,
                       tel: document.getElementById("tel").value,
                       address:document.getElementById("address").value,
                       card: document.getElementById("card").value,
                       name: document.getElementById("name").value,
                       id:document.getElementById("hotelid").innerHTML
                      },
                 //返回数据的格式
                 datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
                 success:function(data){
                     if(!data)
                         alert("信息填写有误");
                     else{
                         alert("注册成功！");
                         location.href="login.jsp";
                     }
                 }
             });
    	}
    } 
    </script>
</head>
<body>
	<%
		HotelManager hotelManager = new HotelManagerImpl();
		String hotelId = hotelManager.getHotelNum();
	%>
    <div style="top:20px;font-family:Lucida Console;font-size: 2em;width:auto">
        <h5 style="margin-left:3%;color:#B20837">Welcome! Hotel <span id="hotelid"><%=hotelId%></span>. </h5>
        <h6 style="margin-left:3%;color:#B20837">Please fill in the blank to register. </h6>
        
        <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="password" id="password" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Password</span>
            </label>
        </span>
        <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="password" id="password2" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Password Again</span>
            </label>
        </span>
        <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="text" id="name" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Hotel Name</span>
            </label>
        </span>
         <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="text" id="tel" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Hotel Tel</span>
            </label>
        </span>
        <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="text" id="address" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Hotel Address</span>
            </label>
        </span>
        <span class="input input--hoshi input_center">
            <input class="input__field input__field--hoshi" type="text" id="card" />
            <label class="input__label input__label--hoshi input__label--hoshi-color-1" >
                <span class="input__label-content input__label-content--hoshi">Bank Card</span>
            </label>
        </span>
        
        <div onclick="register()" class="sim-button button10" style="float:right;right:20%"><span style="font-size:18px;color:#B20837">Register</span></div>      
    </div> 
    	<script src="../js/classie.js"></script>
</body>
    <script src="../js/hotelSignup.js"></script>
</html>