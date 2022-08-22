package com.giftapp.domain.entity;

import com.giftapp.domain.valueobject.Money;
import com.giftapp.domain.valueobject.OrderItemId;
import lombok.Getter;

@Getter
public class OrderItem extends BaseEntity<OrderItemId>{
    private OrderItemId orderItemId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        orderItemId = builder.orderItemId;
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
