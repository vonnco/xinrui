layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index'], function () {
    var $ = layui.$
        , setter = layui.setter;

    //退出登录
    $('#logout').click(function () {
        //清空储存
        layui.data(setter.tableName, null);
        //退出登录
        location.href = '/login/userlogout';
    });
});