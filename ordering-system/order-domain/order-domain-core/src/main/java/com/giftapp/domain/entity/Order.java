package com.giftapp.domain.entity;

import java.util.List;
import java.util.UUID;

import com.giftapp.domain.exception.OrderDomainException;
import com.giftapp.domain.valueobject.CustomerId;
import com.giftapp.domain.valueobject.Money;
import com.giftapp.domain.valueobject.OrderId;
import com.giftapp.domain.valueobject.OrderItemId;
import com.giftapp.domain.valueobject.OrderStatus;
import com.giftapp.domain.valueobject.PersonId;
import com.giftapp.domain.valueobject.StreetAddress;
import com.giftapp.domain.valueobject.TrackingId;
import lombok.Getter;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final PersonId personId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> orderItems;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public void initOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initOrderItems();
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateItemsPrice() {
        if (price == null || !price.isGreeterThanZero()) {
            throw new OrderDomainException();
        }
    }

    private void validateTotalPrice() {
        Money orderItemsTotal = orderItems.stream()
                .map(orderItem -> {
                    validateOrderPrice(orderItem);
                    return orderItem.getSubTotal();
                })
                .reduce(Money.MONEY_ZERO, Money::add);
        if (!price.equals(orderItemsTotal)) {
            throw new OrderDomainException();
        }
    }

    private void validateOrderPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException();
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException();
        }
    }

    void initOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem : orderItems) {
            orderItem.init(super.getId(), new OrderItemId(++itemId));
        }

    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new IllegalStateException();
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new IllegalStateException();
        }
        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel() {
        if (orderStatus != OrderStatus.PAID) {
            throw new IllegalStateException();
        }
        orderStatus = OrderStatus.CANCELING;
    }

    public void cancel() {
        if (orderStatus != OrderStatus.CANCELING || orderStatus != OrderStatus.PENDING) {
            throw new IllegalStateException();
        }
        orderStatus = OrderStatus.CANCELING;
    }

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        personId = builder.personId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        orderItems = builder.orderItems;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    //region builder
    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private PersonId personId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> orderItems;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder personId(PersonId val) {
            personId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            orderItems = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
    //endregion
}
