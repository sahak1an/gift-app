package com.giftapp.domain.dto.port.input.service;

import javax.validation.Valid;

import com.giftapp.domain.dto.CreateOrderCommand;
import com.giftapp.domain.dto.create.CreateOrderResponse;
import com.giftapp.domain.dto.track.TrackOrderQuery;
import com.giftapp.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
