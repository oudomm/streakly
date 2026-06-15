package dev.oudom.streakly.features.habit.log;

import dev.oudom.streakly.common.ApiResponse;
import dev.oudom.streakly.features.habit.log.dto.HabitLogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitLogController {

    private final HabitLogService habitLogService;

    @PostMapping("/{habitId}/logs")
    public ResponseEntity<ApiResponse<HabitLogResponse>> checkIn(@PathVariable UUID habitId) {
        HabitLogResponse data = habitLogService.checkIn(habitId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Check in habit successfully", data));
    }
}
