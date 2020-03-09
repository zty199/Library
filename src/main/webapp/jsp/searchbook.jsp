<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	<link rel="stylesheet" href="css/layui.css" media="all">

  </head>
  
  <body>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
        <legend>查询图书</legend>
    </fieldset>
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">索书号/ISBN</label>
            <div class="layui-input-block">
                <input name="title" class="layui-input" type="text" placeholder="请输入ISBN/索书号" autocomplete="off">
            </div>
        </div>
        <br>
        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="demo2" lay-submit="">查询</button>
        </div>
    </form>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>查询的书的信息</legend>
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
                <tr>
                    <td>索书号14</td>
                    <td>书的种类</td>
                    <td>书的名字</td>
                    <td>ISBN13</td>
                    <td>作者A</td>
                    <td>出版社A</td>
                    <td>某国</td>
                    <td>某时间</td>
                </tr>

            </tbody>
        </table>
    </div>

    <script src="js/layui.all.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
    <script>
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form,
                layer = layui.layer,
                layedit = layui.layedit,
                laydate = layui.laydate;

            //日期
            laydate.render({
                elem: '#date'
            });
            laydate.render({
                elem: '#date1'
            });

            //创建一个编辑器
            var editIndex = layedit.build('LAY_demo_editor');

            //自定义验证规则
            form.verify({
                title: function(value) {
                    if (value.length < 5) {
                        return '标题至少得5个字符啊';
                    }
                },
                pass: [
                    /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
                ],
                content: function(value) {
                    layedit.sync(editIndex);
                }
            });

            //监听指定开关
            form.on('switch(switchTest)', function(data) {
                layer.msg('开关checked：' + (this.checked ? 'true' : 'false'), {
                    offset: '6px'
                });
                layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
            });

            //监听提交
            form.on('submit(demo1)', function(data) {
                layer.alert(JSON.stringify(data.field), {
                    title: '最终的提交信息'
                })
                return false;
            });

            //表单赋值
            layui.$('#LAY-component-form-setval').on('click', function() {
                form.val('example', {
                    "username": "贤心" // "name": "value"
                        ,
                    "password": "123456",
                    "interest": 1,
                    "like[write]": true //复选框选中状态
                        ,
                    "close": true //开关状态
                        ,
                    "sex": "女",
                    "desc": "我爱 layui"
                });
            });

            //表单取值
            layui.$('#LAY-component-form-getval').on('click', function() {
                var data = form.val('example');
                alert(JSON.stringify(data));
            });

        });
    </script>
  </body>
</html>