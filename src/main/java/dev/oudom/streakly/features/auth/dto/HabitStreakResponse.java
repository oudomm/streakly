package dev.oudom.streakly.features.auth.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record HabitStreakResponse(
        UUID habitId,
        int currentStreak,
        int bestStreak,
        double completionRate
) {
}
