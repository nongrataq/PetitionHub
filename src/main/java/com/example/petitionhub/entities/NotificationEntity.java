package com.example.petitionhub.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;

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

    @Column(name = "isRead")
    private boolean isRead;
}

