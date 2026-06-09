package dev.oudom.streakly.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
