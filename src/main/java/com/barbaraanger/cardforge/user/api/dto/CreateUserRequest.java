package com.barbaraanger.cardforge.user.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateUserRequest (
        @NotBlank String name,
        @Email @NotBlank String email
) {
}
