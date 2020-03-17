<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//用户登出清空session信息
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.invalidate();
response.sendRedirect("../jsp/searchbook.jsp");
%>
