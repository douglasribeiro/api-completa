package com.api.service.impl;

import com.api.domain.Users;
import com.api.repositories.UsersRepository;
import com.api.service.UserService;
import com.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario Não Encontrado."));
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }
}
