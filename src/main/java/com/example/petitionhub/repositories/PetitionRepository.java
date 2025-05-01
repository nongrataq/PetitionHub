package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.PetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetitionRepository extends JpaRepository<PetitionEntity, Long> {
    Optional<PetitionEntity> findById(long id);
}