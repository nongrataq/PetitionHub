package com.example.petitionhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.petitionhub.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUsername(String username);
    boolean existsByUsername(String username);
}