package com.example.petitionhub.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 64)
public class TagEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
}