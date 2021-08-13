layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'upload'], function(){
    var $ = layui.$,
        upload = layui.upload;

    //获取jwt令牌
    var jwt = layui.data(layui.setter.tableName)['jwt']

    //执行实例
    var uploadUser = upload.render({
        elem: '#import' //绑定元素
        ,url: '/user/importUser' //上传接口
        ,headers: {"Authorization": 'Bearer ' + jwt} // 请求头
        ,accept: 'file' // 上传校验的文件类型
        ,acceptMime: 'application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' //筛选出的文件类型
        ,exts: 'xlsx|xls' //只允许上传excel文件 //允许上传文件的后缀
        ,before: function (obj) {
            layer.load(); //上传loading
        }
        ,done: function(res){
            layer.closeAll('loading'); //关闭loading
            layer.msg(res.msg, {time: 2000}, function () {
                if (res.success) {
                    parent.layer.close(parent.layer.getFrameIndex(window.name)); //成功再执行关闭
                }
            });
        }
        ,error: function(){
            layer.closeAll('loading'); //关闭loading
        }
    });
})