package com.example.petitionhub.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"images"})
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(
        callSuper = true,
        exclude = {"author", "signatures", "tagEntity", "images"}
)
@AllArgsConstructor
@BatchSize(size = 32)
@Table(name = "petitions")
public class PetitionEntity extends BaseEntity {

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Builder.Default
    private Integer countOfSignatures = 0;

    @OneToMany(mappedBy = "petition", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SignatureEntity> signatures = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tagEntity;

    @OneToMany(mappedBy = "petition", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ImageEntity> images = new ArrayList<>();

}