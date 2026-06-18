package dev.oudom.streakly.features.habit.log;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface HabitLogRepository extends JpaRepository<HabitLog, UUID> {

    List<HabitLog> findByHabitIdOrderByLogDateDesc(UUID habitId);

    long countByHabitIdAndLogDateBetween(UUID habitId, LocalDate start, LocalDate end);

    boolean existsByHabitIdAndLogDate(UUID habitId, LocalDate logDate);
}
