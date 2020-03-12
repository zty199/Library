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

    <title>录入图书 - 图书查询管理系统</title>

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
            <div class="layui-logo">Whatever小组</div>
            
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">主页</a></li>
                <li class="layui-nav-item"><a href="">添加</a></li>

            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item"><a href="">退出</a></li>
            </ul>
        </div>
        
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
            <legend>添加书籍</legend>
        </fieldset>
        
        <form class="layui-form layui-form-pane" action="servlet/AddbookServlet" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">书名</label>
                <div class="layui-input-block">
                    <input name="name" class="layui-input" type="text" placeholder="请输入书名" autocomplete="off" lay-verify="name">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">作者</label>
                <div class="layui-input-block">
                    <input name="writer" class="layui-input" type="text" placeholder="请输入作者" autocomplete="off" lay-verify="writer">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">ISBN</label>
                <div class="layui-input-block">
                    <input id="isbn" name="isbn" class="layui-input" type="text" placeholder="请输入ISBN" autocomplete="off" lay-verify="isbn">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">书籍种类</label>
                <div class="layui-input-inline">
                    <select id="category" name="category" lay-filter="category" lay-verify="required">
                        <option value="" selected="">请选择大类</option>
                        <%
          				CategoryDao dao = new CategoryDao();
          				List<Category> list = dao.getAllCategory();
          				for(int i = 0; i < list.size(); i++) {
          					Category category = list.get(i);
          				%>
                        <option value="<%=category.getId()%>"><%=category.getName()%></option>
                        <%
          				}
          				%>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select id="class" name="class" lay-filter="class" lay-verify="required">
                        <option value="" selected="">请选择小类</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <button type="submit" class="layui-btn" lay-submit="">添加</button>
            </div>
        </form>
    </div>

    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/cascadecategory.js" charset="utf-8"></script>
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