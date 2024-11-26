package com.dbtest.api;

import com.dbtest.entity.Permission;
import com.dbtest.service.PermissionSrv;
import com.dbtest.service.UserSrv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final PermissionSrv permissionSrv;
    private final UserSrv userSrv;


    @GetMapping(value = "test")
    public @ResponseBody
    Object refreshToken() {
        return "test";
    }


    @GetMapping(value = "readData")
    public @ResponseBody Object readData() {
        return permissionSrv.findAll();
    }

    @GetMapping(value = "writeData")
    public @ResponseBody Object writeData(){
        return userSrv.findAll();
    }

}
