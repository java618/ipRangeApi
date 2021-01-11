package com.example.demo.controllers;

import com.example.demo.models.IpRangeModel;
import com.example.demo.models.UserRequestModel;
import com.example.demo.services.IpRangeServiceImpl;
import com.example.demo.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
class IpRangeControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private IpRangeServiceImpl ipRangeService;

    @BeforeEach
    void setUp(){
        ipRangeService = mock(IpRangeServiceImpl.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new IpRangeController(ipRangeService)).build();
        objectMapper = new ObjectMapper();

    }

    @Test
    void findRange() throws Exception {
        String ipAddr = "192.168.0.1";
        IpRangeModel ipRangeModel = new IpRangeModel();
        ipRangeModel.setLowerIp("192.168.0.0");
        ipRangeModel.setUpperIp("192.168.2.255");

        when(ipRangeService.findRange(ipAddr)).thenReturn(ipRangeModel);
        mockMvc.perform(get("/ipRange"+"/"+ipAddr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> java.util.Optional.of(ipRangeModel));
    }

    @Test
    void addRange() throws Exception {
        String ipAddr = "192.168.0.1";
        IpRangeModel ipRangeModel = new IpRangeModel();
        ipRangeModel.setLowerIp("192.168.0.0");
        ipRangeModel.setUpperIp("192.168.2.255");

        when(ipRangeService.findRange(ipAddr)).thenReturn(new ResponseEntity<>("Ip Range successfully created", HttpStatus.OK));
        mockMvc.perform(get("/ipRange"+"/"+ipAddr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> new ResponseEntity<>("Ip Range successfully created", HttpStatus.OK));
    }
}