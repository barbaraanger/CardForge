package com.barbaraanger.cardforge.user.application.usecase;

import com.barbaraanger.cardforge.shared.api.EmailAlreadyExistsException;
import com.barbaraanger.cardforge.user.application.port.out.UserRepositoryPort;
import com.barbaraanger.cardforge.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepositoryPort userRepository;

    @Override
    public User execute(Input input) {
        if (userRepository.existsByEmail(input.email())) {
            throw new EmailAlreadyExistsException(input.email());
        }

        User user = User.create(input.name(), input.email());
        return userRepository.save(user);
    }
}
