package com.barbaraanger.cardforge.user.api.mapper;

import com.barbaraanger.cardforge.user.api.dto.UserResponse;
import com.barbaraanger.cardforge.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public static UserResponse toResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .status(user.getStatus().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
