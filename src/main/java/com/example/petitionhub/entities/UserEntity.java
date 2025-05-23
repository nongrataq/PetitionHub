package com.example.petitionhub.entities;

import com.example.petitionhub.enums.Role;
import com.example.petitionhub.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"petitions", "signatures"})
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
}
