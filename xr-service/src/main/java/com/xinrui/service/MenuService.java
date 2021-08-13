package com.xinrui.service;

import com.xinrui.dao.MenuRepository;
import com.xinrui.framework.common.model.response.CommonCode;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public ResponseResult addMenu(Menu menu) {
        Menu save = menuRepository.save(menu);
        if (save != null) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
