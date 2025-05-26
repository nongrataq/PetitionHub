package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.TagDto;
import com.example.petitionhub.entities.TagEntity;
import com.example.petitionhub.exceptions.TagDoesNotExistException;
import com.example.petitionhub.mappers.TagEntityMapper;
import com.example.petitionhub.repositories.TagRepository;
import com.example.petitionhub.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagEntityMapper tagEntityMapper;


    @Override
    public List<TagDto> getAllTags() {
        return tagEntityMapper.toTagDtos(tagRepository.findAll());
    }

    @Override
    public Optional<TagEntity> findTagByName(String name) {
        return tagRepository.findTagEntityByName(name);
    }
}