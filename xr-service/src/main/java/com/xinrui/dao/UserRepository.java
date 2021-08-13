package com.xinrui.dao;

import com.xinrui.framework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findUserByIdAndPassword(String id,String password);
}
