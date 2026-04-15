package com.barbaraanger.cardforge.user.application.usecase;

import com.barbaraanger.cardforge.shared.api.EmailAlreadyExistsException;
import com.barbaraanger.cardforge.user.application.port.out.UserEventPublisherPort;
import com.barbaraanger.cardforge.user.application.port.out.UserRepositoryPort;
import com.barbaraanger.cardforge.user.domain.User;
import com.barbaraanger.cardforge.user.domain.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepositoryPort userRepository;
    private final UserEventPublisherPort eventPublisher;

    @Override
    public User execute(Input input) {
        if (userRepository.existsByEmail(input.email())) {
            throw new EmailAlreadyExistsException(input.email());
        }
        User user = User.create(input.name(), input.email());
        User saved = userRepository.save(user);

        eventPublisher.publish(UserCreatedEvent.builder()
                .userId(saved.getId())
                .email(saved.getEmail())
                .occuredAt(saved.getCreatedAt())
                .build());
        return saved;
    }
}
