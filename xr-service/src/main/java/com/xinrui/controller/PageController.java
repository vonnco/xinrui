package com.xinrui.controller;

import com.xinrui.Enum.UserSexEnum;
import com.xinrui.api.PageControllerApi;
import com.xinrui.framework.common.Enum.StateEnum;
import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.utils.EnumUtil;
import com.xinrui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PageController implements PageControllerApi {
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/index")
    public String index(String username,Model model){
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取session
        HttpSession session = request.getSession();
        session.removeAttribute("userExt");
        UserExt userExt = userService.findUserByUsername(username);
        model.addAttribute("name",userExt.getName());
        List<Menu> menuList = userExt.getMenuList();
        model.addAttribute("menuList",menuList);
        List<String> menuCodeList = menuList.stream().map(Menu::getMenuCode).collect(Collectors.toList());
        session.setAttribute("menuCodeList",menuCodeList);
        return "index";
    }

    @Override
    @GetMapping("/page/user/list")
    public String userList(Model model){
        Map<String, String> userSexEnumMap = EnumUtil.transEnumToMap(UserSexEnum.class);
        Map<String, String> stateEnumMap = EnumUtil.transEnumToMap(StateEnum.class);
        model.addAttribute("userSexEnumMap",userSexEnumMap);
        model.addAttribute("stateEnumMap",stateEnumMap);
        return "/user/list";
    }

    @Override
    @GetMapping("/page/user/add")
    public String addUser(Model model) {
        Map<String, String> userSexEnumMap = EnumUtil.transEnumToMap(UserSexEnum.class);
        model.addAttribute("userSexEnumMap",userSexEnumMap);
        return "/user/add";
    }

    @Override
    @GetMapping("page/user/edit")
    public String editUser(String id,Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        Map<String, String> userSexEnumMap = EnumUtil.transEnumToMap(UserSexEnum.class);
        model.addAttribute("userSexEnumMap",userSexEnumMap);
        return "/user/edit";
    }

    @Override
    @GetMapping("page/user/import")
    public String importUser() {
        return "/user/import";
    }

    @Override
    @GetMapping("/page/role/list")
    public String roleList(){
        return "/role/list";
    }

    @Override
    @GetMapping("/page/menu/list")
    public String menuList(){
        return "/menu/list";
    }

    @Override
    @GetMapping("/page/operationLog/list")
    public String operationLogList(){
        return "/operationLog/list";
    }
}
