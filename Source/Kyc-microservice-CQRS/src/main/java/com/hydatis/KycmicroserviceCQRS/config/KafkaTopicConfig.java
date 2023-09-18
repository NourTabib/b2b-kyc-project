package com.hydatis.KycmicroserviceCQRS.config;


import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String,Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        return new KafkaAdmin(config);
    }
    @Bean
    public NewTopic createTopic(){
        return new NewTopic("topic-name",5,(short)1);
    }
    @Bean
    public NewTopic createTopic2(){
        return new NewTopic("agent.personne.physique.events",5,(short)1);
    }
}
