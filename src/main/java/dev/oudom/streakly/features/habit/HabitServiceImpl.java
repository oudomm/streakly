package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.exception.AppException;
import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
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
        log.info("create: {}", request);
        User user = getCurentUser();
        Habit habit = habitMapper.habitRequestToHabitResponse(request);
        habit.setUser(user);
        return habitMapper.habitToHabitResponse(habitRepository.save(habit));
    }

    @Override
    public List<HabitResponse> getAll() {
        return List.of();
    }

    @Override
    public HabitResponse getById(UUID id) {
        return null;
    }

    @Override
    public HabitResponse update(UUID id, HabitRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
