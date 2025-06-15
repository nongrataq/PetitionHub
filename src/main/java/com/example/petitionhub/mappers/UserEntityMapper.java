package com.example.petitionhub.mappers;

import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toUserEntity(UserDto userDto);
    UserDto toUserDto(UserEntity userEntity);
}
