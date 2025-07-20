package ru.itis.petitionhub.notifications.projections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public interface NotificationProjection {
    LocalDateTime getDate();
    String getSenderName();
    String getPetitionTitle();
    UUID getPetitionId();
    UUID getId();

    default String getFormattedDate() {
        if (getDate() == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getDate().format(formatter);
    }

}
