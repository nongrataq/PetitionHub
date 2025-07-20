package ru.itis.petitionhub.notifications.signatures.repository;

import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.notifications.signatures.model.SignatureEntity;
import ru.itis.petitionhub.users.model.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignatureRepository extends JpaRepository<SignatureEntity, UUID> {
    @EntityGraph(attributePaths = {"signer", "petition"})
    boolean existsBySignerAndPetition(UserEntity signer, PetitionEntity petition);
}