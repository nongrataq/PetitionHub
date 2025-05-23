package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.PetitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PetitionRepository extends JpaRepository<PetitionEntity, UUID> {
    @EntityGraph(attributePaths = "author")
    Page<PetitionEntity> findAll(Pageable page);
    List<PetitionEntity> findByTitleContainingIgnoreCase(String title);
    @EntityGraph(attributePaths = "author")
    List<PetitionEntity> findPetitionEntitiesByAuthor_Username(String authorUsername);
}