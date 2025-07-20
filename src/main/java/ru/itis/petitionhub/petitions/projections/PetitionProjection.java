package ru.itis.petitionhub.petitions.projections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public interface PetitionProjection {
    UUID getId();
    String getTitle();
    String getDescription();
    int getCountOfSignatures();
    LocalDateTime getDate();
    String getAuthorUsername();
    UUID getAvatarId();

    default String getFormattedDateOfCreation() {
        if (getDate() == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getDate().format(formatter);
    }
}
