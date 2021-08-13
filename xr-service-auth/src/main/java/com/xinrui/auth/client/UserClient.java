package com.xinrui.auth.client;

import com.xinrui.framework.common.client.XrServiceList;
import com.xinrui.framework.model.ext.UserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = XrServiceList.XR_SERVICE)
public interface UserClient {
    @GetMapping("/user/findUserByUsername")
    public UserExt findUserByUsername(@RequestParam("username") String username);
}
