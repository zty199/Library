<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.library.entity.*, com.library.dao.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User) session.getAttribute("user");
int id = Integer.parseInt(request.getParameter("id"));
BookDao dao = new BookDao();
Book book = dao.getInfo(id);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书修改信息 - 图书查询管理系统</title>
    
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
            		<a href="jsp/admin.jsp">欢迎您，管理员<%=user.getUsername()%></a>
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

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>图书信息修改</legend>
        </fieldset>
        
        <form class="layui-form layui-form-pane" action="servlet/ModifybookServlet?id=<%=id%>" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">书名</label>
                <div class="layui-input-block">
                    <input name="name" class="layui-input" type="text" value="<%=book.getName()%>" placeholder="请输入书名" autocomplete="off" lay-verify="name">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">作者</label>
                <div class="layui-input-block">
                    <input name="writer" class="layui-input" type="text" value="<%=book.getWriter()%>" placeholder="请输入作者" autocomplete="off" lay-verify="writer">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">ISBN</label>
                <div class="layui-input-block">
                    <input id="isbn" name="isbn" class="layui-input" type="text" value="<%=book.getISBN()%>" placeholder="请输入ISBN" autocomplete="off" lay-verify="isbn">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">国家地区</label>
                <div class="layui-input-block">
                    <select id="region" name="region" lay-filter="region" lay-verify="required">
                    <%
                    RegionDao dao1 = new RegionDao();
                    if(book.getId_region() == 0) {
                    %>
                        <option value="" selected="">请选择国家地区</option>
                    <%
                    } else {
                    %>
                    	<option value="<%=book.getId_region()%>" selected=""><%=dao1.getInfo(book.getId_region()).getName() + " / " + dao1.getInfo(book.getId_region()).getReference()%></option>
                    <%
                    }
                    %>
                    	<%
          				List<Region> list = dao1.getAllRegion();
          				for(int i = 0; i < list.size(); i++) {
          					Region region = list.get(i);
          				%>
                        <option value="<%=region.getId()%>"><%=region.getName()%></option>
                        <%
          				}
          				%>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">出版社</label>
                <div class="layui-input-block">
                    <select id="publisher" name="publisher" lay-filter="publisher" lay-verify="required">
                    <%
                    PublisherDao dao2 = new PublisherDao();
                    if(book.getId_publisher() == 0) {
                    %>
                        <option value="" selected="">请选择出版社</option>
                    <%
                    } else {
                    %>
                    	<option value="<%=book.getId_publisher()%>" selected=""><%=dao2.getInfo(book.getId_publisher()).getName() + " / " + dao2.getInfo(book.getId_publisher()).getReference()%></option>
                    <%
                    }
                    %>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">书籍种类</label>
                <div class="layui-input-inline">
                    <select id="category" name="category" lay-filter="category" lay-verify="required">
                    <%
                    CategoryDao dao3 = new CategoryDao();
                    %>
                        <option value="<%=book.getId_category()%>" selected=""><%=dao3.getInfo(book.getId_category()).getName()%></option>
                        <%
          				List<Category> list1 = dao3.getAllCategory();
          				for(int i = 0; i < list1.size(); i++) {
          					Category category = list1.get(i);
          				%>
                        <option value="<%=category.getId()%>"><%=category.getName()%></option>
                        <%
          				}
          				%>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select id="class" name="class" lay-filter="class" lay-verify="required">
                    <%
                    ClassDao dao4 = new ClassDao();
                    %>
                        <option value="<%=book.getId_class()%>" selected=""><%=dao4.getInfo(book.getId_class()).getName()%></option>
                    </select>
                </div>
            </div>
            
    		<div class="layui-form-item layui-col-md-offset5">
        		<button type="submit" class="layui-btn" lay-submit=""><i class="layui-icon layui-icon-edit"></i>修改</button>
        		<button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh"></i>重置</button>
        		<button type="button" class="layui-btn layui-btn-primary" onclick="window.location='/Library/jsp/managebook.jsp';"><i class="layui-icon layui-icon-return"></i>返回</button>
    		</div>
    	</form>
    </div>
    
    <script src="layui/layui.all.js" charset="utf-8"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/cascadecategory.js" charset="utf-8"></script>
    <script src="js/cascaderegion.js" charset="utf-8"></script>
    <script>
        layui.use(['form', 'layer'], function () {
            var form = layui.form,
                layer = layui.layer;

            //自定义验证规则
            form.verify({
                name: function (value) {
                    if (value.length == 0) {
                        return '书名不能为空！';
                    }
                },
                writer: function (value) {
                    if (value.length == 0) {
                        return '作者不能为空！';
                    }
                },
                isbn: [
                    /^(?=[0-9]{13}$|(?=(?:[0-9]+[-]){4})[-0-9]{17}$)97[89][-][0-9]{1,5}[-][0-9]+[-][0-9]+[-][0-9]$/,
                    '请检查输入的ISBN格式！'
                ]
            });

            //鼠标悬停提示特效
            $("#isbn").hover(function () {
                layer.tips('注意ISBN格式，例如：978-7-02-000001-1', '#isbn', {
                    tips: [1, '#5FB878'],
                    time: 5000
                });
            }, function () {
                var index = layer.tips();
                layer.close(index);
            });
        });
    </script>
  </body>
</html>
