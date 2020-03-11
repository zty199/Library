<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.library.entity.*, com.library.dao.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询图书 - 图书查询管理系统</title>
    
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
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
        <legend>查询图书</legend>
    </fieldset>
    <form class="layui-form layui-form-pane" action="servlet/SearchbookServlet" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">搜索</label>
            <div class="layui-input-block">
                <input id="content" name="content" class="layui-input" type="text" placeholder="请输入索书号/ISBN/书名/作者" autocomplete="off" lay-verify="content">
            </div>
        </div>
        <br>
        <div class="layui-form-item">
            <button type="submit" class="layui-btn" lay-submit="">查询</button>
        </div>
    </form>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>书籍相关信息</legend>
    </fieldset>

    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>索书号</th>
                    <th>书类</th>
                    <th>书名</th>
                    <th>ISBN</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>国家地区</th>
                    <th>录入时间</th>
                </tr>
            </thead>
            <tbody>
            <%
                if(session.getAttribute("list") != null) {
                	List<Book> list = (List<Book>) session.getAttribute("list");
                	for(int i = 0; i < list.size(); i++) {
                		Book book = list.get(i);
                		RegionDao dao = new RegionDao();
                		PublisherDao dao1 = new PublisherDao();
                		CategoryDao dao2 = new CategoryDao();
                		ClassDao dao3 = new ClassDao();
                		String region = "";
                		String publisher = "";
                		if(book.getId_region() != 0) {
                			region = dao.getInfo(book.getId_region()).getName();
                		} else {
                			region = "未知国家地区";
                		}
                		if(book.getId_publisher() != 0) {
                			publisher = dao1.getInfo(book.getId_publisher()).getName();
                		} else {
                			publisher = "未知出版社";
                		}
                		String category = dao2.getInfo(book.getId_category()).getName();
                		String Class = dao3.getInfo(book.getId_class()).getName();
            %>
                <tr>
                    <td><%=book.getReference()%></td>
                    <td><%=category+"-"+Class%></td>
                    <td><%=book.getName()%></td>
                    <td><%=book.getISBN()%></td>
                    <td><%=book.getWriter()%></td>
                    <td><%=publisher%></td>
                    <td><%=region%></td>
                    <td><%=book.getDate()%></td>
                </tr>
            <%
                	}
                }
            	session.removeAttribute("list");
            %>
            </tbody>
        </table>
    </div>

    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        layui.use(['form', 'layer'], function() {
            var form = layui.form, 
            	layer = layui.layer;
            
            //自定义验证规则
            form.verify({
            	content: function(value) {
                    if (value.length == 0) {
                        return '查询内容不能为空！';
                    }
                }
            });
            
          	//鼠标悬停提示特效
    		$("#content").hover(function() {
    			layer.tips('注意ISBN格式，例如：978-7-02-000001-1', '#content', {
    				tips:[3, '#5FB878'],
    				time: 5000
    			});
    		}, function() {
    			var index = layer.tips();
    			layer.close(index);
    		});
        });
    </script>

  </body>
</html>
