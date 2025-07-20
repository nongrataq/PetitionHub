package ru.itis.petitionhub.notifications.model;

import ru.itis.petitionhub.global.BaseEntity;
import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.users.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@ToString(exclude = {"recipient", "sender", "petition"})
@Entity
@NoArgsConstructor
@BatchSize(size = 32)
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private UserEntity recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "petition_id")
    private PetitionEntity petition;

    @Column(name = "is_read")
    private boolean isRead;
}

