package com.api.config;

import com.api.domain.Users;
import com.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public void startDB(){
        Users u1 = new Users(null, "Douglas", "doulas@mail.com", "123");
        Users u2 = new Users(null, "Daniel", "daniel@mail.com", "123");

        usersRepository.saveAll(List.of(u1,u2));
    }
}
