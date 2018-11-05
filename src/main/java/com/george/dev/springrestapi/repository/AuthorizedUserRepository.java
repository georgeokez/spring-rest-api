package com.george.dev.springrestapi.repository;

import com.george.dev.springrestapi.models.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorizedUserRepository extends JpaRepository<AuthorizedUser, Long> {

    AuthorizedUser findByUsername(String username);


    //List<AuthorizedUser> findByName(String name);

    @Query("UPDATE AuthorizedUser u SET u.username=:lastLogin WHERE u.username = ?#{ principal?.username }")
    @Modifying
    @Transactional
    public void updateLastLogin(@Param("lastLogin")Date lastLogin);
}
