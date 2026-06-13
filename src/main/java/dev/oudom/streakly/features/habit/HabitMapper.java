package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitMapper {
    Habit habitRequestToHabitResponse(HabitRequest habitRequest);
    HabitResponse habitToHabitResponse(Habit habit);
}
