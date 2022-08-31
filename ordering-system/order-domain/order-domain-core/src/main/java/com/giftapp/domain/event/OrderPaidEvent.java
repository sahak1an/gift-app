package com.giftapp.domain.event;

import java.time.ZonedDateTime;

import com.giftapp.domain.entity.Order;

public class OrderPaidEvent extends OrderEvent{

    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
