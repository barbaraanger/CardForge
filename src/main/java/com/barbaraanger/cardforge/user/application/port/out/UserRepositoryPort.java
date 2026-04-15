package com.barbaraanger.cardforge.user.application.port.out;

import com.barbaraanger.cardforge.user.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findById(UUID id);

    boolean existsByEmail(String email);
}
