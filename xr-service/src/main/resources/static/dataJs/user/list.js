layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    jwtDecode: '../js/jwt-decode'
}).use(['index', 'useradmin', 'table','jwtDecode'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table
        , jwtDecode = layui.jwtDecode;

    //获取jwt令牌
    var jwt = layui.data(layui.setter.tableName)['jwt']
    //解析jwt令牌内容
    var jwtDecodeVal = jwt_decode(jwt)

    //加载数据表格
    table.render({
        elem: "#userList",
        url: "/user/findUserList",
        headers: {"Authorization": 'Bearer ' + jwt},
        page: true,
        height: 'full-220',
        cols: [[{type: "checkbox", fixed: "left"}
            , {type: "numbers", fixed: "left", title: "序号"}
            , {field: "username", title: "用户名"}
            , {field: "name", title: "姓名"}
            , {field: "sex", title: "性别", templet:function (data){
                if (data.sex == 0) {
                    return "男"
                } else {
                    return "女"
                }
                }}
            , {field: "tel", title: "手机号"}
            , {field: "email", title: "邮箱"}
            , {title: "修改时间",templet:function (data) {
                    return layui.util.toDateString(data.updateTime, "yyyy-MM-dd HH:mm:ss")
                }}
            , {title: "创建时间",templet:function (data) {
                    return layui.util.toDateString(data.createTime, "yyyy-MM-dd HH:mm:ss")
                }}
            , {title: "状态", templet:function (data){
                if (data.state == 0) {
                    return "正常"
                } else {
                    return "删除"
                }
                }}
            , {title: "操作", width: 150, align: "center", fixed: "right", templet:function (data){
                var html = '';
                if (data.state != 99) {
                    html += '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
                    html += '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>';
                }
                return html;
                }}]],
        text: {
            none: '没有用户数据'
        }
    })

    //监听搜索
    form.on('submit(LAY-user-back-search)', function (data) {
        var field = data.field;

        //执行重载
        table.reload('userList', {
            where: field
        });
    });

    //监听工具条
    table.on("tool(userList)", function (obj) {
        var data = obj.data;
        switch(obj.event){
            case 'del':
                del(data);//删除
                break;
            case 'edit':
                edit(data);//编辑
                break;
        }
    })
    //删除
    function del(data){
        var id = data.id;
        layer.prompt({
            formType: 1,
            title: "敏感操作，请输入密码"
        }, function (value, index) {
            $.ajax({
                url: "/user/checkPassword",
                type: "POST",
                dataType: "json",
                data:{id:jwtDecodeVal.id,password:value},
                headers: {"Authorization": 'Bearer ' + jwt},
                success: function (res) {
                    layer.msg(res.msg, {time: 2000}, function () {
                        if (res.success) {
                            layer.close(index);
                            layer.confirm("确认删除吗？", function (index) {
                                $.ajax({
                                    url: "/user/deleteUser",
                                    type: "POST",
                                    dataType: "json",
                                    data: {_method:'DELETE',ids:id},
                                    headers: {"Authorization": 'Bearer ' + jwt},
                                    success: function (res) {
                                        layer.msg(res.msg, {time: 2000}, function () {
                                            if (res.success) {
                                                table.reload('userList');
                                            }
                                        });
                                    }
                                });
                            })
                        }
                    });
                }
            });
        })
    }
    //编辑
    function edit(data){
        layer.open({
            type: 2,
            title: "编辑",
            content: "/page/user/edit?id="+data.id,
            area: ["420px", "600px"],
            btn: ["确认", "取消"],
            yes: function (index, layero) {
                var iframeWindow = window["layui-layer-iframe" + index]
                    , submitID = "editUser"
                    , submit = layero.find("iframe").contents().find("#" + submitID);
                iframeWindow.layui.form.on("submit(" + submitID + ")", function (data) {
                    $.ajax({
                        url: "/user/editUser",
                        type: "PUT",
                        dataType: "json",
                        data: JSON.stringify(data.field),
                        contentType:"application/json",
                        headers: {"Authorization": 'Bearer ' + jwt},
                        success: function (res) {
                            layer.msg(res.msg, {time: 2000}, function () {
                                if (res.success) {
                                    table.reload('userList');
                                    layer.close(index)
                                }
                            });
                        }
                    });
                });
                submit.trigger("click")
            },
        })
    }

    //事件
    var active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('userList')
                , checkData = checkStatus.data; //得到选中的数据
            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            //获取每行的id
            var ids = new Array();//创建一个数组
            for(var i=0;i<checkData.length;i++){
                ids. push(checkData[i].id)
            }
            //弹出输入框
            layer.prompt({
                formType: 1,
                title: "敏感操作，请输入密码"
            }, function (value, index) {
                $.ajax({
                    url: "/user/checkPassword",
                    type: "POST",
                    dataType: "json",
                    data:{id:jwtDecodeVal.id,password:value},
                    headers: {"Authorization": 'Bearer ' + jwt},
                    success: function (res) {
                        layer.msg(res.msg, {time: 2000}, function () {
                            if (res.success) {
                                layer.close(index);
                                layer.confirm("确认删除吗？", function (index) {
                                    $.ajax({
                                        url: "/user/deleteUser",
                                        type: "POST",
                                        dataType: "json",
                                        data: {_method:'DELETE',ids:ids},
                                        traditional: true,//防止深度序列化
                                        headers: {"Authorization": 'Bearer ' + jwt},
                                        success: function (res) {
                                            layer.msg(res.msg, {time: 2000}, function () {
                                                if (res.success) {
                                                    table.reload('userList');
                                                }
                                            });
                                        }
                                    });
                                })
                            }
                        });
                    }
                });
            });
        }
        , add: function () {
            layer.open({
                type: 2
                , title: '添加'
                , content: '/page/user/add'
                , area: ['420px', '420px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'addUser'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        $.ajax({
                            url: "/user/addUser",
                            type: "POST",
                            dataType: "json",
                            data: data.field,
                            headers: {"Authorization": 'Bearer ' + jwt},
                            success: function (res) {
                                layer.msg(res.msg, {time: 2000}, function () {
                                    if (res.success) {
                                        table.reload('userList');
                                        layer.close(index)
                                    }
                                });
                            }
                        });
                    });

                    submit.trigger('click');
                }
            });
        }
        , import: function () {
            layer.open({
                type: 2
                , title: '添加'
                , content: '/page/user/import'
                , area: ['420px', '420px']
                ,end: function () {
                    table.reload('userList');
                }
            });
        }
        , export: function () {
            window.location.href="/user/exportUser";
        }
    }
    //监听点击事件
    $('.layui-btn.layuiadmin-btn-admin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});