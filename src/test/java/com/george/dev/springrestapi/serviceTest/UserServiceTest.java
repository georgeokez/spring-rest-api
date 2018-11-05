package com.george.dev.springrestapi.serviceTest;

import com.george.dev.springrestapi.models.User;
import com.george.dev.springrestapi.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.List;

/**
 *  Created by George 18/10/2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Test
    public void testUserServiceFeatures(){
        User user1 = new User();

        user1.setFirstname("Tobi");
        user1.setLastname("Akinwale");
        user1.setAge(30);

        // Testing saving a user
        if(!(userService.isUserExist(user1.getFirstname()))) {
            userService.createUser(user1);
        }
          else
              logger.info(String.format("user with firstname: %s already exist.", user1.getFirstname()));



        // Testing retrieving a user
        if((userService.isUserExist("George"))) {
            User userRetrieved = userService.findUserByFirstname("George");
            userRetrieved.setLastname("Okereka");
            userRetrieved.setEmail("georgeokez@gmail.com");

            // Testing update a user
            userService.updateUser(userRetrieved);
        }
        else
            logger.info(String.format("User does not exist"));



        List<User> users = userService.findAllUsers();

        assertNotNull(users);

        //assertThat(users.size(), is(equalTo(2)));

        // Testing deleting a user
        userService.deleteAllUsers();

    }

    @Test
    public void testFindById(){

        User user1 = new User();
        user1.setFirstname("George");
        user1.setAge(30);
        user1.setLastname("Okereka");
        user1.setEmail("georgeokez@gmail.com");

        User user2 = new User();
        user2.setFirstname("Mike");
        user2.setAge(31);
        user2.setLastname("Johnson");
        user2.setEmail("mikejohnson@gmail.com");

        User user3 = new User();
        user3.setFirstname("Thomas");
        user3.setAge(26);
        user3.setLastname("Stick");
        user3.setEmail("Thomasstick@gmail.com");





        // entityManager.persist(user1);

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);


        //User userRetrieved = userRepository.findById(10);

        //assertThat(userRetrieved.getFirstname(), is(equalTo("George")));

    }
}
