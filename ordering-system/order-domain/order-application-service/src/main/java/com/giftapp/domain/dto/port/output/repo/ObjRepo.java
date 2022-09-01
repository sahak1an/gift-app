package com.giftapp.domain.dto.port.output.repo;

import java.util.Optional;

public interface ObjRepo {
    Optional<Object> findObjInformation(Object a);
}
