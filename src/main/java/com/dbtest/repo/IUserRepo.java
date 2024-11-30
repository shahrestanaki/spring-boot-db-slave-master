package com.dbtest.repo;

import com.dbtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IUserRepo extends JpaRepository<User, Long> {

}
