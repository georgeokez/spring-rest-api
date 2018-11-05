package com.george.dev.springrestapi.controllers;

import com.george.dev.springrestapi.models.User;
import com.george.dev.springrestapi.services.GenerateTokenService;
import com.george.dev.springrestapi.services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;

/**
 *  Created by George 18/10/2018
 */

@RestController
@RequestMapping
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private GenerateTokenService generateTokenService;

    // find all users
    @GetMapping("/user/")
    public ResponseEntity<List<User>> listAllUser(HttpServletRequest request, Authentication authentication, Principal principal){
        String userAgent = request.getHeader("user-agent");
        String principalName = principal.getName();
        String user = authentication.getName();
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // find a user by id
    @GetMapping(value = "/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        logger.info(String.format("Fetching User with id: %s",id));

        User user = userService.findUserById(id);
        if (user == null) {

            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    // create a new user
    @PostMapping("/user/")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        if (userService.isUserExist(user.getFirstname())) {
            logger.info(String.format("A User with name %s already exist.",user.getFirstname()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.createUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    // update a user
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        logger.info(String.format("Updating User with id: %s",id));

        User currentUser = userService.findUserById(id);

        if (currentUser==null) {
            logger.info(String.format("User with id: %s is not found",id));
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setEmail(user.getEmail());
        currentUser.setAge(user.getAge());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }


    // delete a user by id
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        logger.info(String.format("Fetching & Deleting User with id: %s",id));

        User user = userService.findUserById(id);
        if (user == null) {
            logger.info(String.format("Unable to delete. User with id: %s not found",id));
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // deleting all users
    @DeleteMapping("/user/")
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // generate access token
    @GetMapping("/token/{resourceId}/{verifierKey}")
    public ResponseEntity<String> generateAccessToken(@PathVariable String resourceId,
                                                      @PathVariable String verifierKey ){
        String token = generateTokenService.generateToken(resourceId, verifierKey);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
