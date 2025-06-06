package com.example.petitionhub.mappers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetitionEntityMapper {
    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "tagName", source = "tagEntity.name")
    PetitionDto toPetitionDto(PetitionEntity entity);
    PetitionEntity toPetitionEntity(PetitionDto dto);
    List<PetitionDto> toPetitionDtos(List<PetitionEntity> entities);
    List<PetitionEntity> toPetitionEntities(List<PetitionDto> dtos);
}
