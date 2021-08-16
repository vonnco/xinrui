layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    xmSelect: '../js/xm-select'
}).use(['index', 'form', 'xmSelect'], function(){
    var $ = layui.$
        ,form = layui.form
        ,xmSelect = layui.xmSelect;

    //获取jwt令牌
    var jwt = layui.data(layui.setter.tableName)['jwt']
    //调用获取角色选项方法
    roleOptions();
    //获取角色选项
    function roleOptions(){
        $.ajax({
            url: "/role/findRoleOptions",
            headers: {"Authorization": 'Bearer ' + jwt},
            type: "GET",
            dataType: "json",
            success: function (result) {
                var data = result;
                var checkData = $('#roleIds').val().split(',');
                var roleOptions = xmSelect.render({
                    el: '#roleOptions',//渲染对象
                    data: data,//显示的数据
                    initValue: checkData,//初始化选中的数据, 需要在data中存在
                    tips: '请选择角色',//默认提示
                    filterable: true,//是否开启搜索
                    direction: 'down',//下拉方向
                    height: '200px',//默认最大高度
                    prop: {//自定义属性名称
                        name: 'roleName',//显示名称
                        value: 'id',//选中值, 当前多选唯一
                    },
                    name: 'roleIds',//表单提交时的name
                    autoRow: true//是否开启自动换行
                });
            }
        });
    }
})