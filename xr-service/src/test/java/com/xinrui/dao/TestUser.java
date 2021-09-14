package com.xinrui.dao;

import com.xinrui.Enum.UserSexEnum;
import com.xinrui.framework.common.Enum.IsEnum;
import com.xinrui.framework.common.Enum.StateEnum;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.User;
import com.xinrui.service.MenuService;
import com.xinrui.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        menu.setMenuName("操作日志管理");
        menu.setMenuCode("sysmanager_operationLog");
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setState(StateEnum.正常.getValue());
        menu.setIsMenu(IsEnum.是.getValue());
        menu.setUrl("/page/operationLog/list");
        menu.setSort(4);
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
    public void test() throws ParseException {

        String[] times = "2021-10".split("-");
        String year = times[0];
        String month = times[1];

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,Integer.parseInt(year) );
        //设置月份
        cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
        //设置日期
        cal.set(Calendar.DATE, 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(cal.getTime());
    }
}
