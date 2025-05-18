package com.example.petitionhub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetitionDto {

    @Size(min = 5, max = 2500, message = "Описание от 5 до 2500 символов.")
    private String description;

    @Size(min = 5, max = 255, message = "Заголовок от 5 до 255 символов.")
    private String title;

    private LocalDateTime date;
    private String authorUsername;
    private int numberOfSignatures;

    public String getFormattedDateOfCreation() {
        if (date == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

}

