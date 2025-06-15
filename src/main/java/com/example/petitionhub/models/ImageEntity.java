package com.example.petitionhub.models;

import com.example.petitionhub.image.ImageType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@BatchSize(size = 32)
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    private String fileName;
    private String fileType;
    private byte[] data;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petition_id")
    private PetitionEntity petition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

