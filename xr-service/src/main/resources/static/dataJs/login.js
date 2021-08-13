layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'user'], function () {
    var $ = layui.$
        , setter = layui.setter
        , admin = layui.admin
        , form = layui.form
        , router = layui.router()
        , search = router.search;

    //渲染form表单
    form.render();

    //如果msg有信息弹出信息
    if($('#msg').val()) {
        top.layer.msg($('#msg').val(), {icon: 5});
    }

    //登录
    form.on('submit(LAY-user-login-submit)', function (obj) {
        return true;
    });

});