package com.hydatis.KycmicroserviceCQRS.command.eventhandler.implementaion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hydatis.KycmicroserviceCQRS.command.eventhandler.EventHandler;
import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@EnableKafka
@Component
public class AgentPersonnePhysiqueEventHandler implements EventHandler<AgentPersonnePhysique> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventHandler.class);
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final String topicName = "agent.personne.physique.events";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public AgentPersonnePhysiqueEventHandler(KafkaTemplate<String,String> kafkaTemplate){
        this.objectMapper.registerModule(new JavaTimeModule());
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public Boolean publish(Event<AgentPersonnePhysique> event) {
        try{
            String data = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topicName,data);
            return true;
        }catch (Exception e){
            LOGGER.error("|| AT AgentPersonnePhysiqueEventHandler ||  ",e);
            return false;
        }
    }

}
