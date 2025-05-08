package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetitionRepository extends JpaRepository<PetitionEntity, Long> {
    @EntityGraph(attributePaths = {"author"})
    List<PetitionEntity> findAllByAuthor(UserEntity author);
}