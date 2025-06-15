package com.example.petitionhub.repositories;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.models.SignatureEntity;
import com.example.petitionhub.models.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignatureRepository extends JpaRepository<SignatureEntity, UUID> {
    @EntityGraph(attributePaths = {"signer", "petition"})
    boolean existsBySignerAndPetition(UserEntity signer, PetitionEntity petition);
}