package com.george.dev.springrestapi.repository;

import com.george.dev.springrestapi.models.UserA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserARepository extends JpaRepository<UserA, Long> {
    UserA findByName(String name);
}
