package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.TagDto;
import com.example.petitionhub.entities.TagEntity;
import com.example.petitionhub.repositories.TagRepository;
import com.example.petitionhub.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;


    @Override
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagDto(tag.getId(), tag.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public TagEntity findTagByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Тег не найден: " + name));
    }
}