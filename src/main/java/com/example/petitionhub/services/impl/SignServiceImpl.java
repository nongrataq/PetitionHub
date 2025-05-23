package com.example.petitionhub.services.impl;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
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

    @Override
    @Transactional
    public void signPetition(PetitionEntity petition, UserEntity signer) {

        SignatureEntity signature = SignatureEntity.builder()
                .signer(signer)
                .petition(petition)
                .build();

        petition.getSignatures().add(signature);
        petition.setCountOfSignatures(petition.getCountOfSignatures() + 1);

        signatureRepository.save(signature);

        petitionRepository.save(petition);
    }

    @Override
    public boolean hasUserSignedPetition(UserEntity signer, PetitionEntity petition) {
        return signatureRepository.existsBySignerAndPetition(signer, petition);
    }
}
