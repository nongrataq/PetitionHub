package ru.itis.petitionhub.users.dto;

import ru.itis.petitionhub.petitions.dto.PetitionDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @NotBlank
    private String username;
    private List<PetitionDto> petitions;
}
