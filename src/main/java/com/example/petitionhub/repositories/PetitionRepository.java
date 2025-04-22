package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.PetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetitionRepository extends JpaRepository<PetitionEntity, Long> {
}