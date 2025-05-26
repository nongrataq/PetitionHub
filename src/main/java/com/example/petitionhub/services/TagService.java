package com.example.petitionhub.services;

import com.example.petitionhub.dto.TagDto;
import com.example.petitionhub.entities.TagEntity;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<TagDto> getAllTags();
    Optional<TagEntity> findTagByName(String name);
}