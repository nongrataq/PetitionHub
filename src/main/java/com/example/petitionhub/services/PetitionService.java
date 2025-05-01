package com.example.petitionhub.services;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.repositories.PetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PetitionService {
    private final PetitionRepository petitionRepository;

    public PetitionEntity createPetition(PetitionEntity petition) {
        return petitionRepository.save(
                PetitionEntity.builder()
                        .title(petition.getTitle())
                        .description(petition.getDescription())
                        .build()
        );
    }

    public PetitionEntity findById(long id) {
        return petitionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Не существующая петиция"));
    }
}