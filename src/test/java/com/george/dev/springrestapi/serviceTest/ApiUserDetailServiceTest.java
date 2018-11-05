package com.george.dev.springrestapi.serviceTest;

import com.george.dev.springrestapi.models.AuthorizedUser;
import com.george.dev.springrestapi.models.User;
import com.george.dev.springrestapi.repository.AuthorizedUserRepository;
import com.george.dev.springrestapi.services.ApiUserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ApiUserDetailServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ApiUserDetailService apiUserDetailService;

    @Autowired
    private AuthorizedUserRepository authorizedUserRepository;


    //@Test
    /*public void testFindById(){

        AuthorizedUser authorizedUser = new AuthorizedUser();

        authorizedUser.setUsername("superman");
        authorizedUser.setPassword("password123");
        authorizedUser.setRole("ADMIN");

        apiUserDetailService.addAuthorizedUser(authorizedUser);


        //User userRetrieved = userRepository.findById(10);

        //assertThat(userRetrieved.getFirstname(), is(equalTo("George")));

    }*/


}
