package com.george.dev.springrestapi.repository;

import com.george.dev.springrestapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  Created by George 18/10/2018
 */


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User findById(long id);

    User findByFirstname(String fname);

    void deleteUserByFirstname(String fname);

}
