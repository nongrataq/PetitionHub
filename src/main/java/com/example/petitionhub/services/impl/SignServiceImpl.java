package com.example.petitionhub.services.impl;

import com.example.petitionhub.entities.NotificationEntity;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.exceptions.AlreadySignedException;
import com.example.petitionhub.repositories.NotificationRepository;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.SignatureRepository;
import com.example.petitionhub.services.SignService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {
    private final PetitionRepository petitionRepository;
    private final SignatureRepository signatureRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void signPetition(PetitionEntity petition, UserEntity signer) {

        if (signatureRepository.existsBySignerAndPetition(signer, petition)) {
            throw new AlreadySignedException(String.format("Пользователь %s уже подписал петицию %s",
                    signer.getUsername(), petition.getTitle()));
        }

        SignatureEntity signature = SignatureEntity.builder()
                .signer(signer)
                .petition(petition)
                .build();


        NotificationEntity notificationEntity = NotificationEntity.builder()
                .sender(signer)
                .petition(petition)
                .recipient(petition.getAuthor())
                .isRead(false)
                .build();

        petition.getSignatures().add(signature);

        notificationRepository.save(notificationEntity);
        petitionRepository.incrementSignatureCount(petition.getId());

        signatureRepository.save(signature);
        petitionRepository.save(petition);
    }

    @Override
    public boolean hasUserSignedPetition(UserEntity signer, PetitionEntity petition) {
       return signatureRepository.existsBySignerAndPetition(signer, petition);
    }
}
