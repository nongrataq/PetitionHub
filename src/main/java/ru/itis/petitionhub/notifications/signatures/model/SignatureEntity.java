package ru.itis.petitionhub.notifications.signatures.model;

import ru.itis.petitionhub.global.BaseEntity;
import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.users.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "signatures",
        uniqueConstraints = @UniqueConstraint(columnNames = {"signer_id", "petition_id"})
)
public class SignatureEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signer_id", nullable = false)
    private UserEntity signer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petition_id", nullable = false)
    private PetitionEntity petition;
}
