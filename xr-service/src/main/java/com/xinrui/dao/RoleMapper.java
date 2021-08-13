package com.xinrui.dao;

import com.github.pagehelper.Page;
import com.xinrui.framework.model.Role;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.UserListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper {
    List<Role> findRoleOptions();
}
