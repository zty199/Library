<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.library.dao.*, com.library.entity.*"%>
<%
//判断用户是否登录
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User) session.getAttribute("user");
if(user == null) {
	out.print("<script>alert('请以管理员身份登录！'); window.location='/Library/jsp/login.jsp'</script>");
}
%>
