<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.library.entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User) session.getAttribute("user");
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
	
	<link rel="stylesheet" href="layui/css/layui.css" media="all">

  </head>
  
  <body>
  	<div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">图书管理系统</div>
            
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="jsp/searchbook.jsp">图书查询</a></li>
                <%
                if(user != null) {
                %>
                <li class="layui-nav-item"><a href="jsp/managebook.jsp">图书管理</a></li>
                <%
                }
                %>
            </ul>
            <ul class="layui-nav layui-layout-right">
            <%
            if(user == null) {
            %>
                <li class="layui-nav-item"><a href="jsp/login.jsp">管理员登录</a></li>
            <%
            } else {
            %>
            	<li class="layui-nav-item">
            		<a href="jsp/login.jsp">欢迎您，管理员<%=user.getUsername()%></a>
            		<dl class="layui-nav-child">
        				<dd class="layui-this">
        					<a href="jsp/logout.jsp">退出登录</a>
        				</dd>
      				</dl>
            	</li>
            <%
            }
            %>
            </ul>
        </div>
          <div class="layui-footer">
    <!-- 底部固定区域 -->
    © Whatever组 Interpreters风格
  </div>
    </div>
    
    <script src="layui/layui.all.js" charset="utf-8"></script>
    
  </body>
</html>
