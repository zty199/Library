<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.library.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = new User();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页 - 图书查询管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="jsp/login.jsp">登录</a>
    <a href="jsp/addbook.jsp">录入图书</a>
    <a href="jsp/searchbook.jsp">查询图书</a>
  </body>
</html>
