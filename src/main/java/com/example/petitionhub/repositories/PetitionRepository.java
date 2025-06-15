package com.example.petitionhub.repositories;

import com.example.petitionhub.petitions.projections.PetitionProjection;
import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PetitionRepository extends JpaRepository<PetitionEntity, UUID> {
    @Query("""
                SELECT
                    p.id as id,
                    p.title as title,
                    p.description as description,
                    p.countOfSignatures as countOfSignatures,
                    p.date as date,
                    u.username as authorUsername,
                    u.avatar.id as avatarId
                FROM PetitionEntity p
                JOIN p.author u
            """)
    Page<PetitionProjection> findAllProjections(Pageable pageable);

    @Query("""
                SELECT
                    p.id as id,
                    p.title as title,
                    p.description as description,
                    p.countOfSignatures as countOfSignatures,
                    p.date as date,
                    u.username as authorUsername,
                    t.name as tagName,
                    u.avatar.id as avatarId
                FROM PetitionEntity p
                JOIN p.author u
                LEFT JOIN p.tagEntity t
                WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))
            """)
    Page<PetitionProjection> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

    @Query("""
                SELECT
                    p.id as id,
                    p.title as title,
                    p.description as description,
                    p.countOfSignatures as countOfSignatures,
                    p.date as date,
                    u.username as authorUsername,
                    t.name as tagName,
                    u.avatar.id as avatarId
                FROM PetitionEntity p
                JOIN p.author u
                JOIN p.tagEntity t
                WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :tag, '%'))
            """)
    Page<PetitionProjection> findByTagEntityNameContainingIgnoreCase(@Param("tag") String tag, Pageable pageable);

    @Query("""
                SELECT
                    p.id as id,
                    p.title as title,
                    p.description as description,
                    p.countOfSignatures as countOfSignatures,
                    p.date as date,
                    u.username as authorUsername,
                    t.name as tagName,
                    u.avatar.id as avatarId
                FROM PetitionEntity p
                JOIN p.author u
                LEFT JOIN p.tagEntity t
                WHERE u.username = :authorUsername
            """)
    List<PetitionProjection> findPetitionEntitiesByAuthor_Username(@Param("authorUsername") String authorUsername);

    @Modifying
    @Query("UPDATE PetitionEntity p SET p.countOfSignatures = p.countOfSignatures + 1 WHERE p.id = :id")
    void incrementSignatureCount(@Param("id") UUID id);

    @Query("SELECT MAX(p.date) from PetitionEntity p where p.author= :user")
    Optional<LocalDateTime> findLastPetitionDateByUser(@Param("user") UserEntity user);


}