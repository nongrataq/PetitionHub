package com.example.petitionhub.services;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.repositories.PetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
                        .author(petition.getAuthor())
                        .build()
        );
    }

    public PetitionEntity findById(long id) {
        return petitionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Несуществующая петиция"));
    }

    public List<PetitionEntity> findAllByAuthor(UserEntity author) {
        return petitionRepository.findAllByAuthor(author);
    }
}