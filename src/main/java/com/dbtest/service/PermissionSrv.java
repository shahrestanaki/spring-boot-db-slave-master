package com.dbtest.service;

import com.dbtest.entity.Permission;
import com.dbtest.repo.IPermissionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PermissionSrv {
    public final IPermissionRepo permissionRepo;


    public List<Permission> findAll() {
        return permissionRepo.findAll();
    }
}
