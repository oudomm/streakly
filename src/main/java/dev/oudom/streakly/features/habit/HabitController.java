package dev.oudom.streakly.features.habit;

import dev.oudom.streakly.common.ApiResponse;
import dev.oudom.streakly.features.habit.dto.HabitRequest;
import dev.oudom.streakly.features.habit.dto.HabitResponse;
import dev.oudom.streakly.features.habit.dto.UpdateHabitRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HabitResponse>> getById(@PathVariable UUID id) {
        HabitResponse data = habitService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Get habit successfully", data));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<HabitResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateHabitRequest request
    ) {
        HabitResponse data = habitService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Update habit successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        habitService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Delete habit successfully"));
    }
}
