package com.hydatis.KycmicroserviceCQRS.config;


import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigConsumer {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
    @Value("${spring.kafka.schema-registry.url}")
    private String schemaRegistryUrl;
    @Value("${spring.kafka.application-id}")
    private String applicationId;
    @Value("${spring.kafka.transaction.state.log.replication.factor}")
    private String replicationFactor;
    @Bean
    public Map<String,Object> ConsumerProps(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,applicationId);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "query-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put("transaction.state.log.replication.factor",1);
//        props.put("schema.registry.url","");
        return props;
    }
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(ConsumerProps());
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
