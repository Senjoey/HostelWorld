<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- c 标签，后面会使用到 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>

<style type="text/css">
    td{
		text-align: center;
		width: 100px;
	}
</style>
</head>
<body>

    <!-- checkUser Action 返回 user1，这里就可以得到用户名-->
    <div align="right">Welcome, ${user1.name } | <a href="logout.jsp">Exit</a>
    </div>
        <center>
    		<table border="1">
    			<tr>
    				<td>user id</td><td>user name</td><td>company</td><td colspan="2" style="center">Options</td>
    			</tr>

                <!-- UserAction 中的 list() 返回 users，遍历它就得到所有的查询结果 -->
    			<c:forEach items="${users}" var="u">

                    <!-- 以表格形式展现所有结果，最后一栏是操作（包含删除和修改） -->
                    <!-- 删除、修改都提交给各自的 Action 处理，通过 id 来判断是哪个用户 -->
    				<tr>
    					<td>${u.id}</td><td>${u.name}</td><td>${u.company}</td><td><a href="user!deleteUser.action?user.id=${u.id}">Delete</a></td><td><a href="user!updateUser.action?user.id=${u.id}">Modify</a></td>
    				</tr>
    			</c:forEach>
    			
    		</table>
    		<br/>

            <!-- 添加用户按钮 -->
    		<input type="submit" value="Add User" onclick="window.location.href='adduser.jsp'"/> 
    	</center>
    	<br>
    	<br>
    	<br>
    	<br>
    	<!-- 查询用户输入框，包含 name、company 两种查询条件 -->
    	<center>
		   	<form action="user!queryUser.action" method="post">
		   		user name<input type="text" name="user.name"/>&nbsp;&nbsp;&nbsp;&nbsp;
		   		company<input type="text" name="user.company"/>&nbsp;&nbsp;&nbsp;&nbsp;	
		   		<input type="submit" value="Search"/>
		   </form>
    	</center>
    	<br>
    	<br>
    	<br><br>


        <!-- tag>0 表示查询结果正常-->
    	<c:if test="${tag>0}">
    	<center>
    		<table border="1">

                <!-- 查询结果也以表格形式展现 -->
    			<tr>
    				<td>user id</td><td>user name</td><td>company</td>
    			</tr>


    			<!-- users2 就是 UserAction 中的 queryUser() 返回的结果，同样是遍历 -->
                <c:forEach items="${users2}" var="u">
    				<tr>
    					<td>${u.id}</td><td>${u.name}</td><td>${u.company }</td>
    				</tr>
    			</c:forEach>
    			
    		</table>
    		
    	</center>
    	</c:if>

</body>
</html>
    	