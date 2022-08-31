package com.giftapp.domain.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.giftapp.domain.entity.Order;
import com.giftapp.domain.event.OrderCanceledEvent;
import com.giftapp.domain.event.OrderCreatedEvent;
import com.giftapp.domain.event.OrderPaidEvent;
import lombok.extern.java.Log;

@Log
public class OrderDomainServiceImpl implements OrderDomainService {

    public static final ZonedDateTime CREATED_AT = ZonedDateTime.now(ZoneId.of("UTC"));

    @Override
    public OrderCreatedEvent validateAndInitOrder(Order order, Object user) {
        order.validateOrder();
        order.initOrder();

        log.info("Order with id" + order.getId().getValue() + "  is init");

        return new OrderCreatedEvent(order, CREATED_AT);
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {

        order.pay();

        log.info("Pay order");

        return new OrderPaidEvent(order, CREATED_AT);
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();

        log.info("Approve order");
    }

    @Override
    public OrderCanceledEvent cancelOrderPayment(Order order, List<String> failMessages) {
        order.cancel();

        log.info("Order canceled");
        return new OrderCanceledEvent(order, CREATED_AT);
    }

    @Override
    public void cancelOrder(Order order, List<String> failMessages) {
        order.cancel();
    }
}
