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
    
    <title>密码修改 - 图书查询管理系统</title>
    
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
  <jsp:include page="isLogin.jsp"></jsp:include>
  <body>
  <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">Whatever小组</div>
            
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
            	<li class="layui-nav-item">欢迎使用图书查询管理系统！</li>
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
      		<legend>密码修改</legend>
    	</fieldset>
	
	<br>
	
    <form class="layui-form" action="servlet/AdminServlet" method="post" style="text-align: center;">
      <div class="layui-form-item">
        <div class="layui-inline">
          <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i>&nbsp;原&nbsp;密&nbsp;码</label>
          <div class="layui-input-inline" style="width: 200px;">
            <input type="password" name="opwd" placeholder="请输入原密码" autocomplete="off" class="layui-input">
          </div>
        </div>
      </div>
      <div class="layui-form-item">
        <div class="layui-inline">
          <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i>&nbsp;新&nbsp;密&nbsp;码</label>
          <div class="layui-input-inline" style="width: 200px;">
            <input type="password" name="pwd" placeholder="请输入新密码" autocomplete="off" class="layui-input" lay-verify="pwd">
          </div>
        </div>
      </div>
      <div class="layui-form-item">
        <div class="layui-inline">
          <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i>&nbsp;确认密码</label>
          <div class="layui-input-inline" style="width: 200px;">
            <input type="password" name="cpwd" placeholder="请输入新密码" autocomplete="off" class="layui-input" lay-verify="cpwd">
          </div>
        </div>
      </div>
      <div style="text-align: center;">
        <button type="submit" class="layui-btn" lay-submit=""><i class="layui-icon layui-icon-edit"></i>修改</button>
        <button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh"></i>重置</button>
        <button type="button" class="layui-btn layui-btn-primary" onclick="window.location='/Library/jsp/managebook.jsp';"><i class="layui-icon layui-icon-return"></i>返回</button>
      </div>
    </form>
    </div>
    
    <script src="layui/layui.all.js" charset="utf-8"></script>
    <script>
        layui.use('form', function () {
            var form = layui.form;
            var opwd, pwd, cpwd, temp;

            //自定义验证规则
            form.verify({
            	pwd: function (value) {
            		pwd = value;
                    if (value.length == 0) {
                        return '新密码不能为空！';
                    }
                }
                ,cpwd: function (value) {
                	cpwd = value;
                    if (value.length == 0) {
                        return '请确认密码！';
                    } else {
                    	if(pwd != cpwd) {
                    		return '两次输入的密码不一致！';
                    	}
                    }
                }
            });
        });
    </script>
    
  </body>
</html>
