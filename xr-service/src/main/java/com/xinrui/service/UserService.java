package com.xinrui.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinrui.dao.UserMapper;
import com.xinrui.dao.UserRepository;
import com.xinrui.framework.common.Enum.StateEnum;
import com.xinrui.framework.common.exception.ExceptionCast;
import com.xinrui.framework.common.model.response.CommonCode;
import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.common.model.response.QueryResult;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.User;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.UserListRequest;
import com.xinrui.framework.model.response.UploadResult;
import com.xinrui.framework.model.response.UserCode;
import com.xinrui.framework.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public UserExt findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    //添加用户
    @Transactional
    public ResponseResult addUser(User user) {
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setState(StateEnum.正常.getValue());
        String roleIds = user.getRoleIds();
        List<String> ids = Arrays.asList(roleIds.split(","));
        //保存用户
        User save = userRepository.save(user);
        if (save != null) {
            int result = userMapper.addRole(ids, save.getId());
            if (result > 0) {
                return new ResponseResult(CommonCode.SUCCESS);
            }
        }
        return new ResponseResult(UserCode.USER_ADD_FAIL);
    }

    //查询用户列表
    public QueryResponseResult findUserList(UserListRequest userListRequest) {
        //设置分页参数
        PageHelper.startPage(userListRequest.getPage(),userListRequest.getLimit());
        //查询用户列表
        Page<User> users = userMapper.findUerList(userListRequest);
        return new QueryResponseResult(CommonCode.SUCCESS,users.getResult(),users.getTotal());
    }

    //校验密码
    public ResponseResult checkPassword(String id, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //根据用户id查询用户
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            //校验密码
            boolean b = passwordEncoder.matches(password, user.getPassword());
            if (b) {
                return new ResponseResult(CommonCode.SUCCESS);
            }
        } else {
            return new ResponseResult(UserCode.USER_ISNULL);
        }
        return new ResponseResult(UserCode.USER_CHECKPASSWORD_FAIL);
    }

    //删除用户(逻辑删除)
    @Transactional
    public ResponseResult deleteUser(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        int result = userMapper.updateUserState(ids, StateEnum.删除.getValue());
        if (result > 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(UserCode.USER_DELETE_FAIL);
    }

    //根据id查询用户
    public User findById(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            List<String> roleIds = userMapper.findRoleIdsByUserId(id);
            user.setRoleIds(StringUtils.join(roleIds.toArray(),","));
            return user;
        }
        return null;
    }

    //编辑用户
    @Transactional
    public ResponseResult editUer(User user) {
        User oldUser = this.findById(user.getId());
        if (!StringUtils.isEmpty(user.getUsername())){
            oldUser.setUsername(user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getName())){
            oldUser.setName(user.getName());
        }
        if (user.getSex() != null){
            oldUser.setSex(user.getSex());
        }
        if (!StringUtils.isEmpty(user.getTel())){
            oldUser.setTel(user.getTel());
        }
        if (!StringUtils.isEmpty(user.getEmail())){
            oldUser.setEmail(user.getEmail());
        }
        oldUser.setUpdateTime(new Date());
        userRepository.save(oldUser);
        List<String> roleIds = new ArrayList<>(Arrays.asList(user.getRoleIds().split(",")));
        List<String> list = new ArrayList<>(Arrays.asList(user.getRoleIds().split(",")));
        List<String> oldRoleIds = new ArrayList<>(Arrays.asList(oldUser.getRoleIds().split(",")));
        roleIds.removeAll(oldRoleIds);
        oldRoleIds.removeAll(list);
        if (!roleIds.isEmpty() && roleIds.size() > 0) {
            userMapper.addRole(roleIds,user.getId());
        }
        if (!oldRoleIds.isEmpty() && oldRoleIds.size() > 0) {
            userMapper.deleteRole(oldRoleIds,user.getId());
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    //导出用户
    public void exportUser(HttpServletRequest request, HttpServletResponse response) {
        String sheetName = "用户信息";
        QueryResponseResult queryResponseResult = this.findUserList(new UserListRequest());
        List<User> userList = queryResponseResult.getData();
        ExportParams params = new ExportParams();
        params.setSheetName(sheetName);//设置sheet名
        Workbook wb = ExcelExportUtil.exportExcel(params, User.class, userList);
        ExcelUtil.exportExcelByEasyPoi(sheetName, wb, request, response);//生成excel并下载
    }

    //导入用户
    public UploadResult importUser(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            ImportParams importParams = new ImportParams();
            List<User> list = ExcelImportUtil.importExcel(inputStream, User.class, importParams);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return new UploadResult(CommonCode.SUCCESS,null);
    }
}
