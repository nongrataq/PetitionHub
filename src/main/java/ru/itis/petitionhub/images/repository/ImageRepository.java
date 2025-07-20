package ru.itis.petitionhub.images.repository;

import ru.itis.petitionhub.images.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
}
