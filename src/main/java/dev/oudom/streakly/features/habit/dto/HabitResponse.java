package dev.oudom.streakly.features.habit.dto;

import java.time.LocalDate;
import java.util.UUID;

public record HabitResponse(
        UUID id,
        String name,
        String description,
        LocalDate createdAt,
        boolean active
) {
}
