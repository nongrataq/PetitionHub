package com.example.petitionhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetitionCreationResultDto {
    private PetitionDto petitionDto;
    private long cooldownLeft;
}
