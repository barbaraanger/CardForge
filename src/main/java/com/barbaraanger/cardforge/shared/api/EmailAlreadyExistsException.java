package com.barbaraanger.cardforge.shared.api;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: %s".formatted(email), HttpStatus.CONFLICT);
    }
}
