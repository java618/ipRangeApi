package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.models.UserRequestModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Boolean> checkUser(UserRequestModel userRequest) {

        boolean isPresent;

        UserEntity user = userRepository.findUserEntitiesByUserNameAndPassword(userRequest.getName(), userRequest.getPassword());

        if (user == null)
            isPresent = false;
        else
            isPresent = true;


        return new ResponseEntity<>(isPresent, HttpStatus.OK);
    }

    public ResponseEntity<String> createUser(UserRequestModel userRequest) {

        String returnInfo;

        UserEntity user = userRepository.findUserByUserName(userRequest.getName());

        if (user == null) {

            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userRequest.getName());
            userEntity.setPassword(userRequest.getPassword());
            userRepository.save(userEntity);

            returnInfo = "User successfully created";
        } else {
            returnInfo = "User with such name exists";

        }

        return new ResponseEntity<>(returnInfo,HttpStatus.OK);
    }
}
