package com.barbaraanger.cardforge.user.domain;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final UserStatus status;
    private final Instant createdAt;

    private User(UUID id, String name, String email, UserStatus status, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.status = Objects.requireNonNull(status);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static User create(String name, String email) {
        return new User(UUID.randomUUID(), name, email, UserStatus.ACTIVE, Instant.now());
    }

    public static User restore(UUID id, String name, String email, UserStatus status, Instant createdAt) {
        return new User(id, name, email, status, createdAt);
    }

    private static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        return name.trim();
    }

    private static String validateEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Valid email is required");
        }
        return email.trim().toLowerCase();
    }

}