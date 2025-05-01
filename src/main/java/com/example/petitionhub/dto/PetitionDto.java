package com.example.petitionhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetitionDto {
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @NotBlank(message = "Обязательное название")
    private String title;
}
