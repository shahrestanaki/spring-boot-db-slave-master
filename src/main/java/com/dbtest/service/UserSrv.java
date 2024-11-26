package com.dbtest.service;

import com.dbtest.entity.User;
import com.dbtest.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserSrv {

    public final IUserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

}

