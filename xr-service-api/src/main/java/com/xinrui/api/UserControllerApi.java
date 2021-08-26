package com.xinrui.api;

import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.UserListRequest;
import com.xinrui.framework.model.response.UploadResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "用户管理接口",description = "用户管理接口，提供用户的增、删、改、查")
public interface UserControllerApi {
    @ApiOperation("根据用户名查询用户信息")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "path",dataType = "String")
    public UserExt findUserByUsername(String username);

    @ApiOperation("添加用户")
    public ResponseResult addUser(User user);

    @ApiOperation("查询用户列表")
    public QueryResponseResult findUserList(UserListRequest userListRequest);

    @ApiOperation("校验密码")
    public ResponseResult checkPassword(String id,String password);

    @ApiOperation("删除用户")
    public ResponseResult deleteUser(List<String> ids);

    @ApiOperation("编辑用户")
    public ResponseResult editUer(User user);

    @ApiOperation("导出用户")
    public void exportUser(HttpServletRequest request, HttpServletResponse response);

    @ApiOperation("导入用户")
    public UploadResult importUser(MultipartFile file);
}
