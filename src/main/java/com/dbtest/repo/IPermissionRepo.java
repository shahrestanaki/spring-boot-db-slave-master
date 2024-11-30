package com.dbtest.repo;

import com.dbtest.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPermissionRepo extends JpaRepository<Permission, Long> {

}
