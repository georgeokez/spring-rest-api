package com.george.dev.springrestapi.services;

import com.george.dev.springrestapi.models.AuthorizedUser;
import com.george.dev.springrestapi.models.UserA;
import com.george.dev.springrestapi.repository.AuthorizedUserRepository;
import com.george.dev.springrestapi.repository.UserARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserARepository userARepository;


    // Read Authorized users from the database
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserA userA = userARepository.findByName(username);

        if (userA == null){
            throw new UsernameNotFoundException(username);
        }

        return toUserDetails(userA);

    }

    private UserDetails toUserDetails(UserA userA) {
        return User.withUsername(userA.getName())
                .password(userA.getPassword())
                .roles(userA.getRole()).build();
    }

    // Update authorized user
    public void updateUserA(UserA userA){
        UserA userA_local = userARepository.findByName(userA.getName());
        if ( userA_local == null){
            throw new UsernameNotFoundException(userA.getName());
        }
        userARepository.save(userA);
    }

    public Boolean isExist(String username){
        UserA userA = userARepository.findByName(username);
        return (userA == null) ? false : true;
    }

    // Creates Authorized user
    public void addUserA(UserA userA){
        userARepository.save(userA);
    }

    // Deletes Authorized user
    public void deleteUserAById(long id){
        userARepository.deleteById(id);
    }
}
