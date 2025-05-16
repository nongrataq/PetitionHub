package com.example.petitionhub.mappers;

import com.example.petitionhub.dto.UserDto;
import com.example.petitionhub.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toUserEntity(UserDto userDto);
    UserDto toUserDto(UserEntity userEntity);
}
