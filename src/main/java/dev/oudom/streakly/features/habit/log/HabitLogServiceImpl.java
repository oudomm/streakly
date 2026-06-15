package dev.oudom.streakly.features.habit.log;

import dev.oudom.streakly.exception.AppException;
import dev.oudom.streakly.features.habit.Habit;
import dev.oudom.streakly.features.habit.HabitRepository;
import dev.oudom.streakly.features.habit.log.dto.HabitLogResponse;
import dev.oudom.streakly.features.user.User;
import dev.oudom.streakly.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitLogServiceImpl implements HabitLogService {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;
    private final HabitLogMapper habitLogMapper;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public HabitLogResponse checkIn(UUID habitId) {
        log.info("checkIn: {}", habitId);

        User user = getCurrentUser();

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new AppException("Habit not found", HttpStatus.NOT_FOUND));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new AppException("Habit doesn't belong to current user", HttpStatus.FORBIDDEN);
        }

        if(habitLogRepository.existsByHabitIdAndLogDate(habitId, LocalDate.now())) {
            throw new AppException("Already checked in today", HttpStatus.CONFLICT);
        }

        HabitLog habitLog = new HabitLog();
        habitLog.setHabit(habit);
        habitLog.setLogDate(LocalDate.now());
        habitLog.setCompleted(true);
        HabitLog saved = habitLogRepository.save(habitLog);
        return habitLogMapper.habitLogToHabitLogResponse(saved);
    }
}
