package dev.oudom.streakly.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        boolean success,
        int status,
        String message
) {
}
