package com.api.service.impl;

import com.api.domain.Users;
import com.api.domain.dto.UsersDTO;
import com.api.repositories.UsersRepository;
import com.api.service.UserService;
import com.api.service.exceptions.DataIntegratyViolationException;
import com.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario Não Encontrado."));
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users create(UsersDTO obj) {
        findByEmail(obj);
        return userRepository.save(modelMapper.map(obj, Users.class));
    }

    @Override
    public Users update(UsersDTO obj) {
        findByEmail(obj);
        return userRepository.save(modelMapper.map(obj, Users.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.delete(findById(id));
    }

    private void findByEmail(UsersDTO obj){
        Optional<Users> user  = userRepository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw  new DataIntegratyViolationException("E-mail ja cadastrado no sistema.");
        }
    }
}
