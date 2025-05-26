package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.PetitionCreationResultDto;
import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.TagEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.exceptions.PetitionDoesNotExistException;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.TagRepository;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;



@RequiredArgsConstructor
@Service
public class PetitionServiceImpl implements PetitionService {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final PetitionEntityMapper petitionEntityMapper;
    private final TagRepository tagRepository;


    @Transactional
    @Override
    public PetitionCreationResultDto createPetitionWithTimeCheck(PetitionDto petitionDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (!userEntity.getPetitions().isEmpty()) {
            LocalDateTime lastDate = userEntity.getPetitions().stream()
                    .max(Comparator.comparing(PetitionEntity::getDate))
                    .get()
                    .getDate();


            long secondsPassed = Duration.between(lastDate, LocalDateTime.now()).getSeconds();
            if (secondsPassed < 20) {
                return new PetitionCreationResultDto(null, 20 - secondsPassed);
            }
        }

        String normalizedTagName = Optional.ofNullable(petitionDto.getTagName())
                .map(String::trim)
                .orElseThrow(() -> new IllegalArgumentException("Тег не может быть пустым."));

        TagEntity tagEntity = tagRepository.findTagEntityByName(normalizedTagName)
                .orElseGet(() -> tagRepository.save(
                        TagEntity.builder()
                                .name(normalizedTagName)
                                .build()
                ));

        PetitionEntity petition = petitionEntityMapper.toPetitionEntity(petitionDto);
        petition.setAuthor(userEntity);
        petition.setTagEntity(tagEntity);

        PetitionEntity savedPetition = petitionRepository.save(petition);
        return new PetitionCreationResultDto(petitionEntityMapper.toPetitionDto(savedPetition), 0);
    }


    @Override
    public PetitionEntity findPetitionById(UUID id) {
        return petitionRepository.findById(id).orElseThrow(() -> new PetitionDoesNotExistException("Несуществующая петиция"));
    }

    @Override
    public Page<PetitionEntity> findAll(Pageable pageable) {
        return petitionRepository.findAll(pageable);
    }

}
