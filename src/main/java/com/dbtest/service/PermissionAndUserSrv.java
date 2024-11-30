package com.dbtest.service;

import com.dbtest.entity.User;
import com.dbtest.repo.IPermissionRepo;
import com.dbtest.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author m.Shahrestanaki @createDate 11/27/2024
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionAndUserSrv {
    public final IPermissionRepo permissionRepo;
    public final IUserRepo userRepo;

    public final PermissionSrv permissionSrv;
    public final UserSrv userSrv;

    public Object getAndSave() {
        User user = new User();
        user.setUserId(5L);
        user.setUserName("test_save");

        permissionSrv.findAll();
        userSrv.saveUser(user);
        permissionSrv.findAll();
        userSrv.findAll();
        return null;
    }
}
