package dev.oudom.streakly.features.habit.log;

import dev.oudom.streakly.features.habit.log.dto.HabitLogResponse;

import java.util.UUID;

public interface HabitLogService {
    HabitLogResponse checkIn(UUID habitId);
}
