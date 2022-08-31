package com.giftapp.domain.valueobject;

import java.util.UUID;

public class PersonId extends BaseId<UUID> {

    protected PersonId(UUID value) {
        super(value);
    }
}
