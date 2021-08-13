layui.config({
    base: '/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    jwtDecode: '../js/jwt-decode'
}).use(['index','jwtDecode'], function () {
    var $ = layui.$
        , setter = layui.setter
        , jwtDecode = layui.jwtDecode;

    //如果token有信息存入data
    if ($('#jwtToken').val()) {
        layui.data(setter.tableName, {
            key: 'jwt'
            , value: $('#jwtToken').val()
        });
    };

    //根据token查询jwt令牌
    /*getUserJwtByToken();
    function getUserJwtByToken() {
        $.ajax({
            url: "/login/getUserjwtByToken?token=" + layui.data(setter.tableName).token,
            type: "GET",
            dataType: "json",
            success: function (res) {
                //解析jwt令牌内容
                var jwtDecodeVal = jwt_decode(res.jwt)
                document.getElementById("name").innerHTML = jwtDecodeVal.name;
                if (res.success) {
                    location.href = 'user/login'; //登录页面
                } else {
                    //请求成功后，写入 access_token
                    layui.data(setter.tableName, {
                        key: 'Authorization'
                        , value: res.jwt
                    });
                }
            }
        });
    }*/

    //退出登录
    $('#logout').click(function () {
        //退出登录
        location.href = '/login/userlogout';
        //清空储存
        layui.data(setter.tableName, null);
    });
});