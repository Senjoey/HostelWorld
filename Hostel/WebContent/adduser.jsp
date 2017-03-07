<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New User</title>
</head>
<body>
    <center> <h1>Add User</h1><br/>
    user id cannot be null or same as others
    
    <form action="user!addUser.action" method="post">
        user id<input type="text" name="user.id"/><br/>
        user name<input type="text" name="user.name"/><br/>
        company<input type="text" name="user.company"/><br/>
        <input type="submit" value="Add"/>
   </form>
   
   </center>
</body>
</html>