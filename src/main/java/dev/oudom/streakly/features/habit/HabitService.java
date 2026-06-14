package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
import dev.oudom.streakly.features.habit.dto.UpdateHabitRequest;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    HabitResponse create(HabitRequest request);
    List<HabitResponse> getAll();
    HabitResponse getById(UUID id);
    HabitResponse update(UUID id, UpdateHabitRequest request);
    void delete(UUID id);
}
