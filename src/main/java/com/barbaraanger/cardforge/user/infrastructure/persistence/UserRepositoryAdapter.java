package com.barbaraanger.cardforge.user.infrastructure.persistence;

import com.barbaraanger.cardforge.user.application.port.out.UserRepositoryPort;
import com.barbaraanger.cardforge.user.domain.User;
import com.barbaraanger.cardforge.user.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final SpringDataUserRepository springDataUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity saved = springDataUserRepository.saveAndFlush(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
