package com.xinrui.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;

@Api(value = "页面跳转接口",description = "页面跳转接口，提供所有菜单页面的跳转")
public interface PageControllerApi {
    @ApiOperation("默认跳转页面")
    public String home();
    @ApiOperation("用户列表页面")
    public String userList(Model model);
    @ApiOperation("添加用户页面")
    public String addUser(Model model);
    @ApiOperation("编辑用户页面")
    public String editUser(String id,Model model);
    @ApiOperation("导入用户页面")
    public String importUser();
    @ApiOperation("角色列表页面")
    public String roleList();
    @ApiOperation("菜单列表页面")
    public String menuList();
}
