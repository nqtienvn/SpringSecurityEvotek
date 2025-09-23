package com.tien.springsecurity.repository;

import com.tien.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        User findByName(String name);
        boolean existsUserByName(String name);
    }
