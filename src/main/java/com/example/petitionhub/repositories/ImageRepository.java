package com.example.petitionhub.repositories;

import com.example.petitionhub.image.ImageTypes;
import com.example.petitionhub.models.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
    List<ImageEntity> findByImageType(ImageTypes imageType);
}
