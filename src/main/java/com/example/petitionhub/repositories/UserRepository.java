package com.example.petitionhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.petitionhub.models.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsUserEntityByUsername(String username);
    Optional<UserEntity> findUserEntityByUsername(String username);
}