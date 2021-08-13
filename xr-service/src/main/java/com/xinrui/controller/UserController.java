package com.xinrui.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.xinrui.api.UserControllerApi;
import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.UserListRequest;
import com.xinrui.framework.model.response.UploadResult;
import com.xinrui.framework.utils.ExcelUtil;
import com.xinrui.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
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
    public ResponseResult addUser(User user) {
        return userService.addUser(user);
    }

    @Override
    @GetMapping("/findUserList")
//    @PreAuthorize("hasAuthority('sysmanager_user_list')")//当用户拥有了course_find_list权限时候方可访问此方法
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
    public ResponseResult deleteUser(@RequestParam("ids") List<String> ids) {
        return userService.deleteUser(ids);
    }

    @Override
    @PutMapping("/editUser")
    public ResponseResult editUer(@RequestBody User user) {
        return userService.editUer(user);
    }

    @Override
    @GetMapping("/exportUser")
    public void exportUser(HttpServletRequest request, HttpServletResponse response) {
        userService.exportUser(request,response);
    }

    @Override
    @PostMapping("/importUser")
    public UploadResult importUser(MultipartFile file) {
        return userService.importUser(file);
    }
}
