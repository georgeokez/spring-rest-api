package com.george.dev.springrestapi.repositoryTest;


import com.george.dev.springrestapi.models.User;
import com.george.dev.springrestapi.models.UserA;
import com.george.dev.springrestapi.repository.UserARepository;
import com.george.dev.springrestapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserARepository userARepository;

    @Test
    public void testFindById(){

        User user1 = new User();
        user1.setFirstname("George");
        user1.setAge(30);
        user1.setLastname("Okez");
        user1.setEmail("georgeokez@gmail.com");

        User user2 = new User();
        user2.setFirstname("Tobi");
        user2.setAge(28);
        user2.setLastname("Abraham");
        user2.setEmail("tobiabramoloy@gmail.com");



       // entityManager.persist(user1);

        userRepository.save(user1);
        userRepository.save(user2);

        //User userRetrieved = userRepository.findById(10);

        //assertThat(userRetrieved.getFirstname(), is(equalTo("George")));

    }

    @Test
    public void insertUserA() {
        UserA userA = new UserA("george","pass123", "ADMIM");
        userARepository.save(userA);
    }

}
