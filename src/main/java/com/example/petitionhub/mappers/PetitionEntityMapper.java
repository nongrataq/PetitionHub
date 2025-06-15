package com.example.petitionhub.mappers;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.petitions.dto.PetitionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetitionEntityMapper {
    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "tagName", source = "tagEntity.name")
    PetitionDto toPetitionDto(PetitionEntity entity);
    PetitionEntity toPetitionEntity(PetitionDto dto);
}
