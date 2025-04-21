package com.example.petitionhub.services;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.repositories.PetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PetitionService {
    
    private final PetitionRepository petitionRepository;

    public void createPetition(String title, String description) {
        PetitionEntity petition = new PetitionEntity();
        petition.setTitle(title);
        petition.setDescription(description);
        petitionRepository.save(petition);
        System.out.println("Петиция сохранена: " + petition);
    }

    public List<PetitionEntity> getAllPetitions() {
        return petitionRepository.findAll();
    }

    public PetitionEntity getPetitionById(Long id) {
        return petitionRepository.findById(id).orElse(null);
    }

    public void deletePetition(Long id) {
        petitionRepository.deleteById(id);
    }

}
