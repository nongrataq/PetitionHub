package com.example.petitionhub.repositories;

import com.example.petitionhub.user.notification.projections.NotificationProjection;
import com.example.petitionhub.models.NotificationEntity;
import com.example.petitionhub.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    @Query("""
    SELECT
        s.username as senderName,
        p.title as petitionTitle,
        p.id as petitionId,
        n.date as date,
        n.isRead as isRead,
        n.id as id
    FROM NotificationEntity n
    JOIN n.sender s
    JOIN n.petition p
    WHERE n.isRead = :read and n.recipient = :recipient
""")
    List<NotificationProjection> findAllByReadAndRecipient(@Param("read") boolean read, @Param("recipient") UserEntity recipient);
}
