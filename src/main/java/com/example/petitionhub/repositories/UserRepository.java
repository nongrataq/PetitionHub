package com.example.petitionhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.petitionhub.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}