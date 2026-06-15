package dev.oudom.streakly.features.habit.log;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface HabitLogRepository extends JpaRepository<HabitLog, UUID> {
    boolean existsByHabitIdAndLogDate(UUID habitId, LocalDate logDate);
}
