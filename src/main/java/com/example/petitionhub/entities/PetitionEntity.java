package com.example.petitionhub.entities;

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
@Table(name = "petitions")
public class PetitionEntity extends BaseEntity {

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToMany(mappedBy = "petition", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignatureEntity> signatures;

    public int getSignatureCount() {
        return signatures != null ? signatures.size() : 0;
    }
}