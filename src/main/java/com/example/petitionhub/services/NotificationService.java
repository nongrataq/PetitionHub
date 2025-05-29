package com.example.petitionhub.services;

import com.example.petitionhub.dto.projections.NotificationProjection;
import com.example.petitionhub.entities.NotificationEntity;
import com.example.petitionhub.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationService {
    List<NotificationProjection> findAllByReadAndRecipient(boolean isRead, UserEntity recipient);
    Optional<NotificationEntity> findNotificationById(UUID id);
    void save(NotificationEntity notification);
}
