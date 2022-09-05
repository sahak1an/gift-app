package com.giftapp.kafka.consumer;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaConsumerImpl<T extends SpecificRecordBase> implements KafkaConsumer<T> {

    @Override
    public void receive(List<T> messages, List<Long> keys, List<Integer> partitions, List<Long> offsets) {

    }
}
