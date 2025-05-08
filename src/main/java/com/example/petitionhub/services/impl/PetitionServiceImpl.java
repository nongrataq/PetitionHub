package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.services.PetitionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PetitionServiceImpl implements PetitionService {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final PetitionEntityMapper petitionEntityMapper;


    @Override
    @Transactional
    public PetitionDto createPetition(PetitionDto petitionDto) { //descr, title

        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //lapay2

        UserEntity currentUser = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not registered")); // password,role,tp td


        PetitionEntity newPetitionEntity = petitionEntityMapper.toPetitionEntity(petitionDto);
        newPetitionEntity.setAuthor(currentUser);

        currentUser.getPetitions().add(newPetitionEntity);



        PetitionEntity savedPetition = petitionRepository.save(newPetitionEntity);

        return petitionEntityMapper.toPetitionDto(savedPetition);
    }

    @Override
    public List<PetitionEntity> findAllByAuthor(UserEntity author) {
        return petitionRepository.findAllByAuthor(author);
    }
}