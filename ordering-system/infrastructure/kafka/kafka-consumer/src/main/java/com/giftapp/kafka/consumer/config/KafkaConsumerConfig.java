package com.giftapp.kafka.consumer.config;

import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

import java.io.Serializable;
import java.util.Map;

import com.giftapp.orderingsystem.kafka.KafkaConfigData;
import com.giftapp.orderingsystem.kafka.KafkaConsumerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig<K extends Serializable, V extends SpecificRecordBase> {
    private final KafkaConsumerConfigData consumerConfig;
    private final KafkaConfigData kafkaConfig;

    @Bean
    public Map<String, Object> consumerConfig() {
        return Map.of(
                BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers(),
                KEY_DESERIALIZER_CLASS_CONFIG, consumerConfig.getKeyDeserializer(),
                VALUE_DESERIALIZER_CLASS_CONFIG, consumerConfig.getValueDeserializer(),
                AUTO_OFFSET_RESET_CONFIG, consumerConfig.getAutoOffsetReset(),
                kafkaConfig.getSchemaRegistryUrlKey(), kafkaConfig.getSchemaRegistryUrlKey(),
                SESSION_TIMEOUT_MS_CONFIG, consumerConfig.getSessionTimeoutMs(),
                HEARTBEAT_INTERVAL_MS_CONFIG, consumerConfig.getHeartbeatIntervalMs(),
                MAX_POLL_INTERVAL_MS_CONFIG, consumerConfig.getMaxPollIntervalMs(),
                MAX_PARTITION_FETCH_BYTES_CONFIG, consumerConfig.getMaxPartitionFetchBytesBoostFactor()
                        * consumerConfig.getMaxPartitionFetchBytesDefault(),
                MAX_POLL_RECORDS_CONFIG, consumerConfig.getMaxPollRecords()
        );
    }

    @Bean
    public ConsumerFactory<K, V> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> listener() {
        var factory = new ConcurrentKafkaListenerContainerFactory<K, V>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(consumerConfig.getBatchListener());
        factory.setConcurrency(consumerConfig.getConcurrencyLevel());
        factory.setAutoStartup(consumerConfig.getAutoStartup());
        factory.getContainerProperties().setPollTimeout(consumerConfig.getPollTimeoutMs());
        return factory;
    }
}
