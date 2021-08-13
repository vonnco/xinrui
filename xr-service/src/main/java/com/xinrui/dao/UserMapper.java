package com.xinrui.dao;

import com.github.pagehelper.Page;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.UserListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    UserExt findUserByUsername(String username);

    Page<User> findUerList(UserListRequest userListRequest);

    int updateUserState(@Param("ids") List<String> ids, @Param("state") Integer state);

    List<String> findRoleIdsByUserId(String userId);

    int addRole(@Param("roleIds") List<String> roleIds,@Param("userId") String userId);

    int deleteRole(@Param("roleIds") List<String> roleIds,@Param("userId")String userId);
}
