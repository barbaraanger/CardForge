package com.barbaraanger.cardforge.user.infrastructure.persistence;

import com.barbaraanger.cardforge.user.api.mapper.UserResponseMapper;
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
    private final UserResponseMapper userResponseMapper;

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity saved = springDataUserRepository.saveAndFlush(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springDataUserRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null) {
            return false;
        }
        return springDataUserRepository.existsByEmail(email.trim());
    }
}
