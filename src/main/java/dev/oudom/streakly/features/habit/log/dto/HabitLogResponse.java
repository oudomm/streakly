package dev.oudom.streakly.features.habit.log.dto;

import java.time.LocalDate;
import java.util.UUID;

public record HabitLogResponse(
        UUID id,
        UUID habitId,
        LocalDate logDate,
        boolean completed
) {
}
