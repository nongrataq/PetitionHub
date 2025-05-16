package com.example.petitionhub.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


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

    @Column(name = "signatures_count")
    private Integer numberOfSignatures;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private UserEntity author;
}
