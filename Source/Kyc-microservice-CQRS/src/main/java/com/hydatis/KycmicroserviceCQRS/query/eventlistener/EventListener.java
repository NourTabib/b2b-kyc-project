package com.hydatis.KycmicroserviceCQRS.query.eventlistener;


import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


public interface EventListener {
    public void consume(String record);
}
