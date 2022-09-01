package com.giftapp.domain.dto.port.output.repo;

import java.util.Optional;

import com.giftapp.domain.entity.Order;
import com.giftapp.domain.valueobject.TrackingId;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId);
}
