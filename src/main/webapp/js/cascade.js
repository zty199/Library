layui.use('form', function() {

    //XMLHttpRequest组件
    var xhr;

    function createXmlHttpRequest() {
        if (window.ActiveXObject) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        } else if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        }
    }

    //Layui-form中各种基于事件的操作，下面会有进一步介绍
    var form = layui.form;

    //处理category下拉框事件
    form.on('select(category)', function(data) {
        var id = data.value;
        if (id > 0) {
            //请求字符串,把区域的id作为页面参数传到后台CascadeServlet
            var url = "servlet/CascadeServlet?id=" + id;
            //创建XMLHttpRequest组件
            xhr = new createXmlHttpRequest();
            //设置回调函数,processReuqest方法的定义在下面
            xhr.onreadystatechange = processRequest;
            //打开与服务器的地址连接
            xhr.open("post", url, true);
            //发送请求
            xhr.send(null);

            //processReuqest方法作为回调方法
            function processRequest() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    //得到完成请求后返回的字串符
                    var str = xhr.responseText;
                    //根据返回的字符串为新创建的select节点添加option节点
                    $('#class').html('<option value="0" selected="">请选择小类</option>');
                    var arr1 = str.split(",");
                    for (var i = 0; i < arr1.length; i++) {
                        var arr2 = arr1[i].split("=");
                        var value = arr2[0];
                        var innerHTML = arr2[1];
                        $('#class').append($("<option/>", {
                            value: value,
                            text: innerHTML
                        }));
                    }
                    //刷新Layui-form渲染
                    form.render();
                }
            }
        }
    });
});