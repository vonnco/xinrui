package com.xinrui.dao;

import com.xinrui.framework.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,String> {
}
