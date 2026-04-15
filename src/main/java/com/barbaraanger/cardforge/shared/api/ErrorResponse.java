package com.barbaraanger.cardforge.shared.api;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        LocalDateTime timestamp,
        Integer statusCode,
        String errorMessage,
        String path
) {
}
