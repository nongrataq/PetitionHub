package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.SignatureRepository;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.SignService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class PetitionServiceImpl implements PetitionService {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final PetitionEntityMapper petitionEntityMapper;


    @Override
    @Transactional
    public PetitionDto createPetition(PetitionDto petitionDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity currentUser = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not registered"));


        PetitionEntity petition = petitionEntityMapper.toPetitionEntity(petitionDto);

        petition.setAuthor(currentUser);
        petition.setDate(LocalDateTime.now());
        currentUser.getPetitions().add(petition);
        petition.setNumberOfSignatures(0);

        PetitionEntity savedPetition = petitionRepository.save(petition);

        return petitionEntityMapper.toPetitionDto(savedPetition);
    }

    @Override
    public List<PetitionDto> findAllByAuthor(UserEntity author) {
        return petitionEntityMapper.toPetitionDtos(petitionRepository.findAllByAuthor(author));
    }

    @Override
    public PetitionEntity findPetitionById(UUID id) {
        return petitionRepository.findById(id).orElseThrow(() -> new NullPointerException("Несуществующая петиция"));
    }

    @Override
    public List<PetitionEntity> findAll() {
        return petitionRepository.findAll();
    }
}