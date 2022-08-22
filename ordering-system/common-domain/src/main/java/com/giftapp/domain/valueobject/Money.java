package com.giftapp.domain.valueobject;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isGreeterThanZero() {
        return this.amount != null && amount.compareTo(ZERO) > 0;
    }

    public boolean isGreeterThan(Money money) {
        return this.amount != null && amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money, int scale) {
        var actual = this.amount.add(money.getAmount()).setScale(scale, HALF_DOWN);

        return new Money(actual);
    }

    public Money add(Money money) {
        return add(money, 2);
    }

    public Money multiply(Money money, int scale) {
        var actual = this.amount.multiply(money.getAmount()).setScale(scale, HALF_DOWN);

        return new Money(actual);
    }

    public Money multiply(Money money) {
        return add(money, 2);
    }

    public Money subtract(Money money, int scale) {
        var actual = this.amount.subtract(money.getAmount()).setScale(scale, HALF_DOWN);

        return new Money(actual);
    }

    public Money subtract(Money money) {
        return add(money, 2);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }
}
