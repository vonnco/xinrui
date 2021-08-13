package com.xinrui.dao;

import com.sun.media.jfxmediaimpl.HostUtils;
import com.xinrui.Enum.UserSexEnum;
import com.xinrui.framework.common.Enum.IsEnum;
import com.xinrui.framework.common.Enum.StateEnum;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.Role;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.service.MenuService;
import com.xinrui.service.RoleService;
import com.xinrui.service.UserService;
import net.minidev.json.JSONUtil;
import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUser {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @Test
    public void testSaveMenu(){
        Menu menu = new Menu();
        menu.setMenuName("菜单管理");
        menu.setMenuCode("sysmanager_menu");
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setState(StateEnum.正常.getValue());
        menu.setIsMenu(IsEnum.是.getValue());
        menu.setSort(3);
        ResponseResult responseResult = menuService.addMenu(menu);
        System.out.println(responseResult);
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setName("管理员");
        user.setSex(UserSexEnum.男.getValue());
        user.setState(StateEnum.正常.getValue());
        userService.addUser(user);
    }

    @Test
    public void testPassword(){
        String xrWebApp = new BCryptPasswordEncoder().encode("XrWebApp");
        System.out.println(xrWebApp);
    }

    @Test
    public void test(){
        String s = new String("abc");
        try {
            Field value = s.getClass().getDeclaredField("value");
            value.setAccessible(true);
            value.set(s,"abcd");
            System.out.println(s);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
