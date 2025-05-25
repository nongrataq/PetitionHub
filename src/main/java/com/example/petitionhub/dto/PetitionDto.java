package com.example.petitionhub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetitionDto {
    @Size(min = 5, max = 2500, message = "Описание от 5 до 2500 символов.")
    @Pattern(
            regexp = "^(?=.*[А-Яа-яA-Za-z])[\\s\\S]+$",
            message = "Описание должно содержать хотя бы одну букву (кириллицу или латиницу)."
    )
    private String description;

    @Size(min = 5, max = 255, message = "Заголовок от 5 до 255 символов.")
    @Pattern(
            regexp = "^(?=.*[А-Яа-яA-Za-z])[А-Яа-яA-Za-z0-9\\s.,!?\"'()\\-–—+=*#%@;^`~&$€£¥₽]+$",
            message = "Заголовок должен содержать буквы (кириллицу или латиницу), цифры и разрешённые символы."
    )
    private String title;
    private UUID id;
    private LocalDateTime date;
    private String authorUsername;
    private int countOfSignatures;

    private String tagName;

    public String getFormattedDateOfCreation() {
        if (date == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }





}

