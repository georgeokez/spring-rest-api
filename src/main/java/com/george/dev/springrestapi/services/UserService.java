package com.george.dev.springrestapi.services;

import com.george.dev.springrestapi.models.User;
import com.george.dev.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Created by George 18/10/2018
 */


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        return userRepository.findById(id);
    }

    public User findUserByFirstname(String firstName){
        return userRepository.findByFirstname(firstName);
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public boolean isUserExist(String firstName){
        User user = userRepository.findByFirstname(firstName);
        return (user == null) ? false : true;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public void deleteUserByFirstname(String firstName){
        userRepository.deleteUserByFirstname(firstName);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
