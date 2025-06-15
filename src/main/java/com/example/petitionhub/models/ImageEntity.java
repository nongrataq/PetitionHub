package com.example.petitionhub.models;

import com.example.petitionhub.image.ImageTypes;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@BatchSize(size = 32)
@Table(name = "avatars")
public class ImageEntity extends BaseEntity {
    private String fileName;
    private String fileType;

    @Enumerated(EnumType.STRING)
    ImageTypes imageType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data")
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petition_id")
    private PetitionEntity petition;





}

