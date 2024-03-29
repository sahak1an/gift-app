package com.giftapp.domain.event;

import java.time.ZonedDateTime;

import com.giftapp.domain.entity.Order;

public class OrderCreatedEvent extends OrderEvent {

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
