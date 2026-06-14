package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
import dev.oudom.streakly.features.habit.dto.UpdateHabitRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HabitMapper {
    Habit habitRequestToHabitResponse(HabitRequest habitRequest);
    HabitResponse habitToHabitResponse(Habit habit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateHabitRequestToHabit(UpdateHabitRequest request, @MappingTarget Habit habit);
}
