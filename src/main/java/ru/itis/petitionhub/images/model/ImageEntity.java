package ru.itis.petitionhub.images.model;

import ru.itis.petitionhub.global.BaseEntity;
import ru.itis.petitionhub.images.enums.ImageType;
import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.users.model.UserEntity;
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

