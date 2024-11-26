package com.dbtest.repo;

import com.dbtest.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface IPermissionRepo extends JpaRepository<Permission, Long> {

}
