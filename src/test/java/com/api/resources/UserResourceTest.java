package com.api.resources;

import com.api.domain.Users;
import com.api.domain.dto.UsersDTO;
import com.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceTest {
    public static final int ID = 1;
    public static final String NOME = "Douglas";
    public static final String EMAIL = "douglas@email.com";
    public static final String PASSWD = "123";

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Users users;
    private UsersDTO usersDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsers(){
        users = new Users(ID, NOME, EMAIL, PASSWD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWD);
    }
}