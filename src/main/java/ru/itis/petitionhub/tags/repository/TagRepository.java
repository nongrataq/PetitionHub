package ru.itis.petitionhub.tags.repository;

import ru.itis.petitionhub.tags.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {
    Optional<TagEntity> findTagByName(String name);
}