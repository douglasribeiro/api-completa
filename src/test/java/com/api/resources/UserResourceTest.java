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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
    private UsersDTO usersDTO = new UsersDTO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(users);
        when(mapper.map(any(),any())).thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsersDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
    }

    @Test
    void whenFindAllThenReturnOjListDTO() {
        when(service.findAll()).thenReturn(List.of(users));
        when(mapper.map(any(),any())).thenReturn(usersDTO);

        ResponseEntity<List<UsersDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UsersDTO.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(users);

        ResponseEntity<UsersDTO> response = resource.create(usersDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(usersDTO)).thenReturn(users);
        when(mapper.map(any(),any())).thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = resource.update(ID, usersDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsersDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<UsersDTO> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void startUsers(){
        users = new Users(ID, NOME, EMAIL, PASSWD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWD);
    }
}