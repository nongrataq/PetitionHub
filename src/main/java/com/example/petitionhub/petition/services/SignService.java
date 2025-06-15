package com.example.petitionhub.petition.services;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.models.SignatureEntity;
import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.exceptions.AlreadySignedException;
import com.example.petitionhub.repositories.SignatureRepository;
import com.example.petitionhub.user.notification.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignService {
    private final SignatureRepository signatureRepository;
    private final PetitionService petitionService;
    private final NotificationService notificationService;


    @Transactional
    public void signPetition(PetitionEntity petition, UserEntity signer) {
        checkIfAlreadySigned(signer, petition);

        SignatureEntity signature = createSignature(signer, petition);
        petition.getSignatures().add(signature);

        notificationService.createNotification(signer, petition);

        petitionService.incrementSignatureCount(petition);
    }

    public boolean hasUserSignedPetition(UserEntity signer, PetitionEntity petition) {
        return signatureRepository.existsBySignerAndPetition(signer, petition);
    }

    private void checkIfAlreadySigned(UserEntity signer, PetitionEntity petition) {
        if (signatureRepository.existsBySignerAndPetition(signer, petition)) {
            throw new AlreadySignedException("Пользователь уже подписал петицию");
        }
    }

    private SignatureEntity createSignature(UserEntity signer, PetitionEntity petition) {
        SignatureEntity signature = SignatureEntity.builder()
                .signer(signer)
                .petition(petition)
                .build();

        return signatureRepository.save(signature);
    }
}
