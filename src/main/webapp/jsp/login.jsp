<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.library.entity.*, com.library.dao.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User) session.getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录 - 图书查询管理系统</title>
    
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
                <li class="layui-nav-item"><a href="jsp/addbook.jsp">图书录入</a></li>
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
            		<a href="jsp/admin.jsp">欢迎您，管理员&nbsp;<%=user.getUsername()%></a>
            		<dl class="layui-nav-child">
        				<dd class="layui-this">
        					<a href="jsp/logout.jsp"><i class="layui-icon layui-icon-logout"></i>&nbsp;退出登录</a>
        				</dd>
      				</dl>
            	</li>
            <%
            }
            %>
            </ul>
        </div>
        
  		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; text-align: center;">
      		<legend>管理员登录</legend>
    	</fieldset>
	
	<br>
	
    <form class="layui-form" action="servlet/LoginServlet" method="post" style="text-align: center;">
      <div class="layui-form-item">
        <div class="layui-inline">
          <label class="layui-form-label"><i class="layui-icon layui-icon-username"></i>&nbsp;用户名</label>
          <div class="layui-input-inline" style="width: 200px;">
            <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
          </div>
        </div>
      </div>
      <div class="layui-form-item">
        <div class="layui-inline">
          <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i>&nbsp;密&nbsp;&nbsp;&nbsp;码</label>
          <div class="layui-input-inline" style="width: 200px;">
            <input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
          </div>
        </div>
      </div>
      <div style="text-align: center;">
        <button type="submit" class="layui-btn" lay-submit="">登录</button>
        <button type="button" class="layui-btn layui-btn-primary" onclick="javascript:history.back(-1);">返回</button>
      </div>
    </form>
      <div class="layui-footer">
    <!-- 底部固定区域 -->
    © Whatever组 Interpreters风格
  </div>
    </div>
    
    <script src="layui/layui.all.js" charset="utf-8"></script>
    
  </body>
</html>
