package com.barbaraanger.cardforge.user.infrastructure.messaging;

import com.barbaraanger.cardforge.user.application.port.out.UserEventPublisherPort;
import com.barbaraanger.cardforge.user.domain.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventPublisherImpl implements UserEventPublisherPort {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(UserCreatedEvent userCreatedEvent) {
        log.info("Publishing UserCreatedEvent for userId=%s".formatted(userCreatedEvent.userId()));
    }
}
