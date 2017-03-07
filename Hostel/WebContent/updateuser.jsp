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
<title>Modify User Info</title>
</head>
<body>
    <center> <h1>Modify User Info</h1><br/>

    <form action="user!updateUserImpl.action" method="post">
        <input type="hidden" name="user.id" value="${user.id}"  readonly="readonly"/><br/>
        user name<input type="text" name="user.name" value="${user.name}"/><br/>
        company<input type="text" name="user.company" value="${user.company}"/><br/>
        <input type="submit" value="OK"/>
   </form>

   </center>

</body>
</html>