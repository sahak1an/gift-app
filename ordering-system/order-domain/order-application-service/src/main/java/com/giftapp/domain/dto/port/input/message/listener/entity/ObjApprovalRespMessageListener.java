package com.giftapp.domain.dto.port.input.message.listener.entity;

public interface ObjApprovalRespMessageListener {
    void orderApproved(Object abc);
    void orderRejected(Object abc);
}
