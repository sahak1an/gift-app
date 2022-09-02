package com.giftapp.kafka.producer.service;

import java.io.Serializable;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.util.concurrent.ListenableFutureCallback;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    void send(String topicName, K key, V message, ListenableFutureCallback<Object> callback);
}
