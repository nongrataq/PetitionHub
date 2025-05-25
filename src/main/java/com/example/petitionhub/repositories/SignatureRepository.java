package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SignatureRepository extends JpaRepository<SignatureEntity, UUID> {
    @EntityGraph(attributePaths = {"signer", "petition"})
    boolean existsBySignerAndPetition(UserEntity signer, PetitionEntity petition);
}