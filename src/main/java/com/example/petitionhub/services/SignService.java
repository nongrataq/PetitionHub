package com.example.petitionhub.services;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;

public interface SignService {
    void signPetition(PetitionEntity petition, UserEntity signer);
    boolean hasUserSignedPetition(UserEntity signer, PetitionEntity petition);
}
