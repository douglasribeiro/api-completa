package com.api.service;

import com.api.domain.Users;
import com.api.domain.dto.UsersDTO;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
    Users create(UsersDTO obj);
    Users update(UsersDTO obj);
}
