package ru.itis.petitionhub.petitions.mapper;

import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.petitions.dto.PetitionDto;
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
