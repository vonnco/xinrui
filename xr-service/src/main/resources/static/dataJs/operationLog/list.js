layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'useradmin', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    //获取jwt令牌
    var jwt = layui.data(layui.setter.tableName)['jwt']

    //加载数据表格
    table.render({
        elem: "#operationLogList",
        url: "/operationLog/findOperationLogList",
        headers: {"Authorization": 'Bearer ' + jwt},
        page: true,
        height: 'full-220',
        cols: [[{type: "checkbox", fixed: "left"}
            , {field: "id", title: "ID"}
            , {field: "operateClass", title: "操作类"}
            , {field: "operateMethod", title: "操作方法"}
            , {field: "returnClass", title: "返回值类型"}
            , {field: "returnValue", title: "返回值"}
            , {field: "operateUser", title: "操作人"}
            , {title: "操作时间",templet:function (data) {
                debugger
                    return layui.util.toDateString(data.operateTime, "yyyy-MM-dd HH:mm:ss")
                }}
            , {field: "costTime", title: "耗时"}]],
        text: {
            none: '没有操作日志'
        }
    })

    //监听搜索
    form.on('submit(LAY-operationLog-back-search)', function (data) {
        var field = data.field;

        //执行重载
        table.reload('operationLogList', {
            where: field
        });
    });
});