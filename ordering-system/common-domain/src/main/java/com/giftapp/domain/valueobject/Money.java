package com.giftapp.domain.valueobject;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    public boolean isGreeterThanZero() {
        return this.amount != null && amount.compareTo(ZERO) > 0;
    }

    public boolean isGreeterThan(Money money) {
        return this.amount != null && amount.compareTo(money.amount()) > 0;
    }

    public Money add(Money money, int scale) {
        var actual = this.amount.add(money.amount()).setScale(scale, HALF_DOWN);

        return new Money(actual);
    }

    public Money add(Money money) {
        return add(money, 2);
    }

    public Money multiply(Money money, int scale) {
        var actual = this.amount.multiply(money.amount()).setScale(scale, HALF_DOWN);

        return new Money(actual);
    }

    public Money multiply(Money money) {
        return add(money, 2);
    }

    public Money subtract(Money money, int scale) {
        var actual = this.amount.subtract(money.amount()).setScale(scale, HALF_DOWN);

        return new Money(actual);
    }

    public Money subtract(Money money) {
        return add(money, 2);
    }
}
