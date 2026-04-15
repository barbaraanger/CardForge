package com.barbaraanger.cardforge.user.api.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record UserResponse (
        UUID id,
        String name,
        String email,
        String status,
        Instant createdAt
) {
}
