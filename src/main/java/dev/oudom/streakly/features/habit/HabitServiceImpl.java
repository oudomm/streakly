package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.exception.AppException;
import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
import dev.oudom.streakly.features.habit.dto.UpdateHabitRequest;
import dev.oudom.streakly.features.user.User;
import dev.oudom.streakly.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final HabitMapper habitMapper;

    private User getCurentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public HabitResponse create(HabitRequest request) {
        User user = getCurentUser();
        log.info("create: {}, by user: {}", request, user.getUsername());
        Habit habit = habitMapper.habitRequestToHabitResponse(request);
        habit.setUser(user);
        return habitMapper.habitToHabitResponse(habitRepository.save(habit));
    }

    @Override
    public List<HabitResponse> getAll() {
        User user = getCurentUser();
        log.info("getAll by user: {}", user.getUsername());
        return habitRepository.findByUserId(user.getId()).stream().map(habitMapper::habitToHabitResponse).toList();
    }

    @Override
    public HabitResponse getById(UUID id) {
        User user = getCurentUser();
        log.info("gtById: {}, by user: {}", id, user.getUsername());
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new AppException("Habit not found", HttpStatus.NOT_FOUND));
        if (!habit.getUser().getId().equals(user.getId())) {
            throw new AppException("Habit doesn't belong to current user", HttpStatus.FORBIDDEN);
        }
        return habitMapper.habitToHabitResponse(habit);
    }

    @Override
    public HabitResponse update(UUID id, UpdateHabitRequest request) {
        User user = getCurentUser();
        log.info("update id: {}, request: {}, by user: {}", id, request, user.getUsername());
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new AppException("Habit not found", HttpStatus.NOT_FOUND));
        if (!habit.getUser().getId().equals(user.getId())) {
            throw new AppException("Habit doesn't belong to current user", HttpStatus.FORBIDDEN);
        }
        habitMapper.updateHabitRequestToHabit(request, habit);
        Habit saved = habitRepository.save(habit);
        return habitMapper.habitToHabitResponse(saved);
    }

    @Override
    public void delete(UUID id) {

    }
}
