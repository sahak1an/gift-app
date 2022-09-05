package com.giftapp.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException{

    public KafkaProducerException() {
    }

    public KafkaProducerException(String message) {
        super(message);
    }
}
