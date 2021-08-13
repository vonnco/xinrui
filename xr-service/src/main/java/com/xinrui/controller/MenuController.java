package com.xinrui.controller;

import com.xinrui.api.MenuControllerApi;
import com.xinrui.api.RoleControllerApi;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.Role;
import com.xinrui.service.MenuService;
import com.xinrui.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController implements MenuControllerApi {
    @Autowired
    private MenuService menuService;

    @Override
    @PostMapping("/addMenu")
    public ResponseResult addMenu(Menu menu) {
        return menuService.addMenu(menu);
    }
}
