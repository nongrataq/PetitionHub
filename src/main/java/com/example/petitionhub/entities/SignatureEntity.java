package com.example.petitionhub.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "signatures",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "petition_id"})
)
public class SignatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petition_id", nullable = false)
    private PetitionEntity petition;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;
}