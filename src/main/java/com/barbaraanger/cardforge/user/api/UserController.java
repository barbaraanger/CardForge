package com.barbaraanger.cardforge.user.api;

import com.barbaraanger.cardforge.user.api.dto.CreateUserRequest;
import com.barbaraanger.cardforge.user.api.dto.UserResponse;
import com.barbaraanger.cardforge.user.api.mapper.UserResponseMapper;
import com.barbaraanger.cardforge.user.application.usecase.CreateUserUseCase;
import com.barbaraanger.cardforge.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody final CreateUserRequest createUserRequest) {
        User user = createUserUseCase.execute(new CreateUserUseCase.Input(
                createUserRequest.name(),
                createUserRequest.email()));
        return UserResponseMapper.toResponse(user);
    }
}