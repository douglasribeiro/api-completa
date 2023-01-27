package com.api.service;

import com.api.domain.Users;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
}
