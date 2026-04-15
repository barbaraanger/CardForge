package com.barbaraanger.cardforge.user.application.usecase;

import com.barbaraanger.cardforge.user.domain.User;

public interface CreateUserUseCase {
    User execute(Input input);

    record Input(String name, String email) {
    }
}
