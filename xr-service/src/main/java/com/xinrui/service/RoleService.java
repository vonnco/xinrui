package com.xinrui.service;

import com.xinrui.dao.RoleMapper;
import com.xinrui.dao.RoleRepository;
import com.xinrui.framework.common.model.response.CommonCode;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    //添加角色
    public ResponseResult addRole(Role role) {
        Role save = roleRepository.save(role);
        if (save != null) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    //查询角色选项
    public List<Role> findRoleOptions() {
        return roleMapper.findRoleOptions();
    }
}
