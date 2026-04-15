package com.barbaraanger.cardforge.user.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record UserCreatedEvent(
        UUID userId, String email, Instant occuredAt
) {
}
