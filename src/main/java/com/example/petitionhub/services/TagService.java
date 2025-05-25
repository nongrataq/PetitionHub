package com.example.petitionhub.services;

import com.example.petitionhub.dto.TagDto;
import com.example.petitionhub.entities.TagEntity;

import java.util.List;

public interface TagService {
    List<TagDto> getAllTags();
    TagEntity findTagByName(String name);
}