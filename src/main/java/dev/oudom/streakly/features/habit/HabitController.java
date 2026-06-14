package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.common.ApiResponse;
import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<ApiResponse<HabitResponse>> create(@Valid @RequestBody HabitRequest request) {
        HabitResponse data = habitService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Habit created successfully", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HabitResponse>>> getAll() {
        List<HabitResponse> data = habitService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Get all habits successfully", data));
    }
}
