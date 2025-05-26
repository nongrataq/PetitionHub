package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {
    Optional<TagEntity> findTagEntityByName(String name);
}