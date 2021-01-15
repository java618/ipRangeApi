package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.models.UserRequestModel;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class UserServiceImplTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }


    @Test
    void tryCheckUserMethod_andReturnTrue() {
        UserEntity user = new UserEntity();
        user.setUserName("alex");
        user.setPassword("1");

        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setName(user.getUserName());
        userRequestModel.setPassword(user.getPassword());

        when(userRepository.findUserEntitiesByUserNameAndPassword("alex", "1")).thenReturn(user);

        ResponseEntity<Boolean> found = userService.checkUser(userRequestModel);

        assertThat(found.getBody()).isTrue();
    }

    @Test
    void tryCreateUserMethod_andReturnSuccess() {

        UserEntity user = new UserEntity();
        user.setUserName("alex");
        user.setPassword("1");

        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setName(user.getUserName());
        userRequestModel.setPassword(user.getPassword());

        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<String> found = userService.createUser(userRequestModel);

        assertThat(found.getBody()).isEqualTo("User successfully created");

    }
}