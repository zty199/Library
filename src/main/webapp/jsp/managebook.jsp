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

    <title>图书管理 - 图书查询管理系统</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="layui/css/layui.css" media="all">

</head>
<jsp:include page="isLogin.jsp"></jsp:include>

<body>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">图书查询管理系统</div>

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

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>图书管理</legend>
        </fieldset>

        <div class="layui-form" style="padding:0px 30px">
            <form class="layui-form layui-form-pane" action="servlet/ManagebookServlet" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">搜索</label>
                    <div class="layui-input-block">
                        <input id="content" name="content" class="layui-input" type="text"
                            placeholder="请输入ID/索书号/ISBN/书名/作者" autocomplete="off" lay-verify="content">
                    </div>
                </div>

                <div class="layui-form-item layui-col-md-offset5">
                    <button type="submit" class="layui-btn  layui-col-md3" lay-submit=""><i class="layui-icon layui-icon-search"></i>搜索</button>
                </div>
            </form>

            <div>
                <table class="layui-table">
                    <colgroup>
                        <col width="50">
                        <col width="100">
                        <col width="200">
                        <col width="200">
                        <col width="200">
                        <col width="100">
                        <col width="150">
                        <col width="150">
                        <col width="200">
                        <col width="200">
                    </colgroup>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>索书号</th>
                            <th>书类</th>
                            <th>书名</th>
                            <th>ISBN</th>
                            <th>作者</th>
                            <th>出版社</th>
                            <th>国家地区</th>
                            <th>录入时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    List<Book> list = new ArrayList<Book>();
                    if(session.getAttribute("list") == null) {
                	    BookDao dao = new BookDao();
                	    list = dao.getAllBook();
                    } else {
                	    list = (List<Book>) session.getAttribute("list");
                    }
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
                            <td><%=book.getId()%></td>
                            <td><%=book.getReference()%></td>
                            <td><%=category+"-"+Class%></td>
                            <td><%=book.getName()%></td>
                            <td><%=book.getISBN()%></td>
                            <td><%=book.getWriter()%></td>
                            <td><%=publisher%></td>
                            <td><%=region%></td>
                            <td><%=book.getDate()%></td>
                            <td>
                                <button type="button" class="layui-btn layui-btn-sm" onclick="window.location='/Library/jsp/modifybook.jsp?id=<%=book.getId()%>';"><i class="layui-icon layui-icon-edit"></i>修改</button>
                                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" onclick="judge(<%=book.getId()%>)"><i class="layui-icon layui-icon-delete"></i>删除</button>
                            </td>
                        </tr>
                    <%
                        }
            	        session.removeAttribute("list");
            	    %>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="layui-footer" style="text-align: center;">
            <!-- 底部固定区域 -->
            © Whatever组 Interpreters风格
        </div>
    </div>

    <script src="layui/layui.all.js" charset="utf-8"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        layui.use(['form', 'layer'], function () {
            var layer = layui.layer;

            //鼠标悬停提示特效
            $("#content").hover(function () {
                layer.tips('注意ISBN格式，例如：978-7-02-000001-1', '#content', {
                    tips: [3, '#5FB878'],
                    time: 5000
                });
            }, function () {
                var index = layer.tips();
                layer.close(index);
            });
        });

        //根据选择删除的图书将ID传到后台
        function judge(id) {
            if (confirm("确认删除这本书？")) {
                window.location = '/Library/servlet/DeletebookServlet?id=' + id;
            } else {
                window.location = '/Library/jsp/managebook.jsp';
            }
        }
    </script>

</body>

</html>