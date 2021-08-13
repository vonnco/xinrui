package com.xinrui.api;

import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Role;
import com.xinrui.framework.model.ext.UserExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "角色管理接口",description = "角色管理接口，提供角色的增、删、改、查")
public interface RoleControllerApi {
    @ApiOperation("添加角色信息")
    public ResponseResult addRole(Role role);
    @ApiOperation("查询角色选项")
    public List<Role> findRoleOptions(Role role);
}
