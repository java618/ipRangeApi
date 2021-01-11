package com.example.demo.controllers;

import com.example.demo.models.UserRequestModel;
import com.example.demo.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
class UserControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserServiceImpl.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        objectMapper = new ObjectMapper();

    }

    @Test
    void checkUser() throws Exception {

        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setName("alex");
        userRequestModel.setPassword("1");
//        ObjectNode jsonNodes = objectMapper.createObjectNode();
//        jsonNodes.putPOJO("REQUESTMODEL",userRequestModel);


        when(userService.checkUser(userRequestModel)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestModel)))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> new ResponseEntity<>(true, HttpStatus.OK));

    }

    @Test
    void creatUser() throws Exception {
        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setName("alex");
        userRequestModel.setPassword("1");

        when(userService.createUser(userRequestModel)).thenReturn(new ResponseEntity<>("User successfully created", HttpStatus.OK));
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestModel)))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> new ResponseEntity<>("User successfully created", HttpStatus.OK));
    }
}