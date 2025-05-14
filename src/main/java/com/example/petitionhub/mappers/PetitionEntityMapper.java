package com.example.petitionhub.mappers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetitionEntityMapper {
    PetitionDto toPetitionDto(PetitionEntity entity);
    PetitionEntity toPetitionEntity(PetitionDto dto);
}