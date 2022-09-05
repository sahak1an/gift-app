package com.giftapp.kafka.producer.service;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PreDestroy;

import com.giftapp.kafka.producer.exception.KafkaProducerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {
    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
        log.debug("Sending message={} to topic={}", message, topicName);

        try {
            ListenableFuture<SendResult<K, V>> resultFuture = kafkaTemplate.send(topicName, key, message);

            resultFuture.addCallback(callback);
        } catch (KafkaException ex) {
            log.error("Error on kafka producer with key {} and exception {}", key, ex.getLocalizedMessage());

            throw new KafkaProducerException("Error on kafka producer");
        }
    }

    @PreDestroy
    public void closeConnection(){
        Optional.ofNullable(kafkaTemplate).ifPresent(KafkaTemplate::destroy);
    }
}
