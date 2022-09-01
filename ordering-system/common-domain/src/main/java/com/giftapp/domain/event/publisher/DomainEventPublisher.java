package com.giftapp.domain.event.publisher;

import com.giftapp.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent<?>> {
    void publish(T event);
}
