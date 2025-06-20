package com.example.petitionhub.models;

import com.example.petitionhub.auth.enums.Role;
import com.example.petitionhub.auth.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"avatar"})
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(
        exclude = {
                "petitions",
                "signatures",
                "receivedNotifications",
                "sendNotifications",
                "avatar"
        },
        callSuper = true)
@BatchSize(size = 64)
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<PetitionEntity> petitions = new ArrayList<>();

    @OneToMany(mappedBy = "signer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<SignatureEntity> signatures = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
    private List<NotificationEntity> receivedNotifications;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<NotificationEntity> sendNotifications;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "avatar_id")
    private ImageEntity avatar;
}
