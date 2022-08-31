package com.giftapp.domain.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.giftapp.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {

    @NonNull
    private final UUID customerId;

    @NonNull
    private final UUID objectId;

    @NonNull
    private final BigDecimal price;

    @NonNull
    private final List<OrderItem> orderItems;

}
