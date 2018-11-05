package com.george.dev.springrestapi.services;

import com.george.dev.springrestapi.models.AuthorizedUser;
import com.george.dev.springrestapi.models.dao.ApiUserPrincipal;
import com.george.dev.springrestapi.repository.AuthorizedUserRepository;
import com.george.dev.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Service
public class ApiUserDetailService implements UserDetailsService {

    @Autowired
    private AuthorizedUserRepository authorizedUserRepository;


    // Read Authorized users from the database
    @Override
    public UserDetails loadUserByUsername(String username){
        AuthorizedUser authorizedUser = authorizedUserRepository.findByUsername(username);

        if (authorizedUser == null){
            throw new UsernameNotFoundException(username);
        }

        return new ApiUserPrincipal(authorizedUser);
    }

    // Update authorized user
    public void updateAuthorizedUser(AuthorizedUser authorizedUser){
        AuthorizedUser authorizedUser1 = authorizedUserRepository.findByUsername(authorizedUser.getUsername());
        if ( authorizedUser1 == null){
            throw new UsernameNotFoundException(authorizedUser.getUsername());
        }
        authorizedUserRepository.save(authorizedUser);
    }

    public Boolean isExist(String username){
        AuthorizedUser authorizedUser = authorizedUserRepository.findByUsername(username);
        return (authorizedUser == null) ? false : true;
    }

    // Creates Authorized user
    public void addAuthorizedUser(AuthorizedUser authorizedUser){
        authorizedUserRepository.save(authorizedUser);
    }

    // delete authorized user
    public void deleteAuthorizedUserById(long id){
        authorizedUserRepository.deleteById(id);
    }

}
