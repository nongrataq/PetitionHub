package com.example.petitionhub.user.notification.service;

import com.example.petitionhub.models.NotificationEntity;
import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.user.notification.projections.NotificationProjection;
import com.example.petitionhub.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<NotificationProjection> findAllByReadAndRecipient(boolean isRead, UserEntity recipient) {
        return notificationRepository.findAllByReadAndRecipient(isRead, recipient);
    }

    public Optional<NotificationEntity> findNotificationById(UUID id) {
        return notificationRepository.findById(id);
    }

    public void createNotification(UserEntity signer, PetitionEntity petition) {
        NotificationEntity notificationEntity = NotificationEntity.builder()
                .sender(signer)
                .petition(petition)
                .recipient(petition.getAuthor())
                .isRead(false)
                .build();

        notificationRepository.save(notificationEntity);
    }

    public void save(NotificationEntity notification) {
        notificationRepository.save(notification);
    }
}
