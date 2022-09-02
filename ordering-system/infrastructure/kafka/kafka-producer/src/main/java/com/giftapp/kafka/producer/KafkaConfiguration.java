package com.giftapp.kafka.producer;

import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.COMPRESSION_TYPE_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.LINGER_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.io.Serializable;
import java.util.Map;

import com.giftapp.orderingsystem.kafka.KafkaConfigData;
import com.giftapp.orderingsystem.kafka.KafkaProducerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration<K extends Serializable, V extends SpecificRecordBase> {

    private final KafkaProducerConfigData producerConfig;
    private final KafkaConfigData kafkaConfig;

    @Bean
    public Map<String, Object> producerConfig() {
        return Map.of(
                BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers(),
                kafkaConfig.getSchemaRegistryUrlKey(), kafkaConfig.getSchemaRegistryUrl(),
                KEY_SERIALIZER_CLASS_CONFIG, producerConfig.getKeySerializerClass(),
                VALUE_SERIALIZER_CLASS_CONFIG, producerConfig.getValueSerializerClass(),
                BATCH_SIZE_CONFIG, producerConfig.getBatchSize() * producerConfig.getBatchSizeBoostFactor(),
                LINGER_MS_CONFIG, producerConfig.getLingerMs(),
                COMPRESSION_TYPE_CONFIG, producerConfig.getCompressionType(),
                ACKS_CONFIG, producerConfig.getAcks(),
                REQUEST_TIMEOUT_MS_CONFIG, producerConfig.getRequestTimeoutMs(),
                RETRIES_CONFIG, producerConfig.getRetryCount()
        );
    }

    public PrFactory
}
