package com.barbaraanger.cardforge.user.application.port.out;

import com.barbaraanger.cardforge.user.domain.UserCreatedEvent;

public interface UserEventPublisherPort {
    void publish(UserCreatedEvent userCreatedEventEvent);
}
