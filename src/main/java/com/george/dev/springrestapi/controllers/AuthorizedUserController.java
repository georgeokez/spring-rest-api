package com.george.dev.springrestapi.controllers;

import com.george.dev.springrestapi.models.AuthorizedUser;
import com.george.dev.springrestapi.models.User;
import com.george.dev.springrestapi.models.UserA;
import com.george.dev.springrestapi.services.ApiUserDetailService;
import com.george.dev.springrestapi.services.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/AuthorizedUser")
public class AuthorizedUserController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private MyUserDetailService myUserDetailService;


    @PostMapping("/add/")
    public ResponseEntity<Void> createUser(@RequestBody UserA userA) {

        if (myUserDetailService.isExist(userA.getName())) {
            logger.info(String.format("A User with name %s already exist.",userA.getName()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        myUserDetailService.addUserA(userA);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}

