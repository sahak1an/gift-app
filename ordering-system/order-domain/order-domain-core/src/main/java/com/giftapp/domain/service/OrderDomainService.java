package com.giftapp.domain.service;

import java.util.List;

import com.giftapp.domain.entity.Order;
import com.giftapp.domain.event.OrderCanceledEvent;
import com.giftapp.domain.event.OrderCreatedEvent;
import com.giftapp.domain.event.OrderPaidEvent;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitOrder(Order order, Object user);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCanceledEvent cancelOrderPayment(Order order, List<String> failMessages);

    void cancelOrder(Order order,List<String> failMessages);
}
