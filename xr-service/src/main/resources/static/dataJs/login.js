layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    jwtDecode: '../js/jwt-decode'
}).use(['index', 'user','jwtDecode'], function () {
    var $ = layui.$
        , setter = layui.setter
        , admin = layui.admin
        , form = layui.form
        , router = layui.router()
        , search = router.search;

    //渲染form表单
    form.render();

    //登录
    form.on('submit(LAY-user-login-submit)', function (data) {
        $.ajax({
            url: "/login/userlogin",
            type: "POST",
            dataType: "json",
            data: data.field,
            success: function (res) {
                if (res.success) {
                    layui.data(setter.tableName, {
                        key: 'jwt'
                        , value: res.token
                    });
                    //解析jwt令牌内容
                    var jwtDecodeVal = jwt_decode(res.token)
                    location.href="/index?username="+jwtDecodeVal.sub;
                } else {
                    layer.msg(res.msg,{time: 2000});
                }
            }
        });
        return false;
    });

});