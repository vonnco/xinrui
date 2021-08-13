package com.xinrui.dao;

import com.xinrui.framework.model.Role;
import com.xinrui.framework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
