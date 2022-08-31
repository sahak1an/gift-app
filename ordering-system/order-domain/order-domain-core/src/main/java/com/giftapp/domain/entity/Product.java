package com.giftapp.domain.entity;

import com.giftapp.domain.valueobject.Money;
import com.giftapp.domain.valueobject.ProductId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;
}
