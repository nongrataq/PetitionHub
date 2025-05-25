package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.PetitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PetitionRepository extends JpaRepository<PetitionEntity, UUID> {
    @EntityGraph(attributePaths = "author")
    Page<PetitionEntity> findAll(Pageable page);
    Page<PetitionEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    @EntityGraph(attributePaths = "author")
    List<PetitionEntity> findPetitionEntitiesByAuthor_Username(String authorUsername);
    @Modifying
    @Query("UPDATE PetitionEntity p SET p.countOfSignatures = p.countOfSignatures + 1 WHERE p.id = :id")
    void incrementSignatureCount(@Param("id") UUID id);
}