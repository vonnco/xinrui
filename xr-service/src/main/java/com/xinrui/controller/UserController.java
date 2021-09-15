package com.xinrui.controller;

import com.xinrui.aop.OperateLog;
import com.xinrui.api.UserControllerApi;
import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.UserListRequest;
import com.xinrui.framework.model.response.UploadResult;
import com.xinrui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/findUserByUsername")
    public UserExt findUserByUsername(@RequestParam("username") String username) {
        return userService.findUserByUsername(username);
    }

    @Override
    @PostMapping("/addUser")
    @OperateLog
    //@PreAuthorize("hasAuthority('sysmanager_user_add')")//当用户拥有了sysmanager_user_add权限时候方可访问此方法
    public ResponseResult addUser(User user) {
        return userService.addUser(user);
    }

    @Override
    @GetMapping("/findUserList")
    //@RequiresPermissions("sysmanager_user_findUserList")//当用户拥有了sysmanager_user_add权限时候方可访问此方法
    public QueryResponseResult findUserList(UserListRequest userListRequest) {
        QueryResponseResult queryResponseResult = userService.findUserList(userListRequest);
        if (queryResponseResult.isSuccess()) {
            queryResponseResult.setCode(0);
        }
        return queryResponseResult;
    }

    @Override
    @PostMapping("/checkPassword")
    public ResponseResult checkPassword(String id, String password) {
        return userService.checkPassword(id,password);
    }

    @Override
    @DeleteMapping("/deleteUser")
    @OperateLog
    public ResponseResult deleteUser(@RequestParam("ids") List<String> ids) {
        return userService.deleteUser(ids);
    }

    @Override
    @PutMapping(value = "/editUser")
    @OperateLog
    public ResponseResult editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @Override
    @GetMapping("/exportUser")
    public void exportUser(HttpServletRequest request, HttpServletResponse response) {
        userService.exportUser(request,response);
    }

    @Override
    @PostMapping("/importUser")
    @OperateLog
    public UploadResult importUser(MultipartFile file) {
        return userService.importUser(file);
    }
}
