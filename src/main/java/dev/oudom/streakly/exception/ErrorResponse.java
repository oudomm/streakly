package dev.oudom.streakly.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message
) {
}
