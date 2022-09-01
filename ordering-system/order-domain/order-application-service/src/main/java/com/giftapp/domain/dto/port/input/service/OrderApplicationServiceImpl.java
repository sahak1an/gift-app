package com.giftapp.domain.dto.port.input.service;

import com.giftapp.domain.dto.CreateOrderCommand;
import com.giftapp.domain.dto.create.CreateOrderResponse;
import com.giftapp.domain.dto.track.TrackOrderQuery;
import com.giftapp.domain.dto.track.TrackOrderResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Log
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
