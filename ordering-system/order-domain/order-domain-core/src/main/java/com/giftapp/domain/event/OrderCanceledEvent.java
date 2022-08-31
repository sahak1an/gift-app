package com.giftapp.domain.event;

import java.time.ZonedDateTime;

import com.giftapp.domain.entity.Order;

public class OrderCanceledEvent extends OrderEvent {

    public OrderCanceledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
