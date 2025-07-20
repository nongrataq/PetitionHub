package ru.itis.petitionhub.petitions.services;

import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.notifications.signatures.model.SignatureEntity;
import ru.itis.petitionhub.users.model.UserEntity;
import ru.itis.petitionhub.exceptions.AlreadySignedException;
import ru.itis.petitionhub.notifications.signatures.repository.SignatureRepository;
import ru.itis.petitionhub.notifications.service.NotificationService;
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
