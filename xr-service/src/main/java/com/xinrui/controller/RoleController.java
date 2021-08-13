package com.xinrui.controller;

import com.xinrui.api.RoleControllerApi;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Role;
import com.xinrui.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController implements RoleControllerApi {
    @Autowired
    private RoleService roleService;

    @Override
    @PostMapping("/addRole")
    public ResponseResult addRole(Role role) {
        return roleService.addRole(role);
    }

    @Override
    @GetMapping("/findRoleOptions")
    public List<Role> findRoleOptions(Role role) {
        return roleService.findRoleOptions();
    }
}
