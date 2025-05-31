package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.projections.NotificationProjection;
import com.example.petitionhub.entities.NotificationEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.repositories.NotificationRepository;
import com.example.petitionhub.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationProjection> findAllByReadAndRecipient(boolean isRead, UserEntity recipient) {
        return notificationRepository.findAllByReadAndRecipient(isRead, recipient);
    }

    @Override
    public Optional<NotificationEntity> findNotificationById(UUID id) {
        return notificationRepository.findById(id);
    }


    @Override
    public void save(NotificationEntity notification) {
        notificationRepository.save(notification);
    }

}
