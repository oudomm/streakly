package dev.oudom.streakly.features.habit.log;

import dev.oudom.streakly.features.habit.log.dto.HabitLogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HabitLogMapper {

    @Mapping(target = "habitId", source = "habit.id")
    HabitLogResponse habitLogToHabitLogResponse(HabitLog habitLog);
}
