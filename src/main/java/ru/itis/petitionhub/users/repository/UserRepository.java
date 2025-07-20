package ru.itis.petitionhub.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.petitionhub.users.model.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsUserEntityByUsername(String username);
    Optional<UserEntity> findUserEntityByUsername(String username);
}