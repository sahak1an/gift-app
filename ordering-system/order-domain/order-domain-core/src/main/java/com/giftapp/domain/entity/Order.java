package com.giftapp.domain.entity;

import java.util.List;

import com.giftapp.domain.valueobject.CustomerId;
import com.giftapp.domain.valueobject.Money;
import com.giftapp.domain.valueobject.OrderId;
import com.giftapp.domain.valueobject.OrderStatus;
import com.giftapp.domain.valueobject.PersonId;
import com.giftapp.domain.valueobject.StreetAddress;
import com.giftapp.domain.valueobject.TrackingId;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final PersonId personId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> orderItems;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

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

    public CustomerId getCustomerId() {
        return customerId;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

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
}
