package com.xinrui.api;

import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "菜单管理接口",description = "菜单管理接口，提供菜单的增、删、改、查")
public interface MenuControllerApi {
    @ApiOperation("添加菜单信息")
    public ResponseResult addMenu(Menu menu);
}
