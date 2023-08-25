package com.hydatis.KycmicroserviceCQRS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hydatis.KycmicroserviceCQRS.command.eventhandler.implementaion.AgentPersonnePhysiqueEventHandler;
import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.command.model.Banque;
import com.hydatis.KycmicroserviceCQRS.command.model.CategorieSocioProfesionnelle;
import com.hydatis.KycmicroserviceCQRS.command.model.Document;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import com.hydatis.KycmicroserviceCQRS.config.ConfigProducer;
import com.hydatis.KycmicroserviceCQRS.events.CreateEvent;
import com.hydatis.KycmicroserviceCQRS.events.UpdateEvent;
import com.hydatis.KycmicroserviceCQRS.query.eventlistener.implementation.PersonnePhysiqueEventListener;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@ComponentScan(basePackages = "com.hydatis.KycmicroserviceCQRS.*")
public class KycMicroserviceCqrsApplication {
	public static void main(String[] args) {
		SpringApplication.run(KycMicroserviceCqrsApplication.class,args);
		AgentPersonnePhysique agentPersonnePhysique = new AgentPersonnePhysique();
		agentPersonnePhysique.setId(5L);
		agentPersonnePhysique = AgentPersonnePhysique.builder()
				.document(new Document())
				.addresseCourier("test")
				.adressePerso("aaa")
				.beneficiaireEffectifs(AgentPersonnePhysique.builder().nom("nour").build())
				.categorieSocioProfesionnelle(CategorieSocioProfesionnelle.builder().build())
				.nom("nour")
				.dateDeNaissance(LocalDateTime.now())
				.titulaireDuCompte(agentPersonnePhysique)
				.estBeneficiareEffectifs(false)
				.build();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		objectMapper.registerModule(new JavaTimeModule());
		String data = null;
		try {
			data = objectMapper.writeValueAsString(agentPersonnePhysique);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		try {
			AgentPersonnePhysique two = objectMapper.readValue(data,AgentPersonnePhysique.class);
			UpdateEvent<AgentPersonnePhysique> event = new UpdateEvent<>(two);
			//data = objectMapper.writeValueAsString(event);
			AgentPersonnePhysique agentPersonnePhysique1 = new AgentPersonnePhysique();
			agentPersonnePhysique1.setCategorieSocioProfesionnelle(new CategorieSocioProfesionnelle());
			agentPersonnePhysique1.setBanqueEnRelation(Arrays.asList(new Banque()));
			agentPersonnePhysique1.getCategorieSocioProfesionnelle().setTypeAgent(TypeAgent.RETRAITE);
			//data = objectMapper.writeValueAsString(agentPersonnePhysique1);
			Map<String,Object> map = objectMapper.readValue(data,Map.class);

			String data1 = objectMapper.writeValueAsString(event);
			Properties properties = new Properties();

			properties.put("bootstrap.servers", "localhost:29092");
			properties.put("acks", "all");
			properties.put("retries", "10");
			properties.put("key.serializer", StringSerializer.class.getName());
			properties.put("value.serializer", StringSerializer.class.getName());

			KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
			ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>("agent.personne.physique.events",data1);
			System.out.println("[ PRODUCER ] - "+event);
			kafkaProducer.send(producerRecord, new Callback() {
				@Override
				public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
					if (exception == null) {
						System.out.println(" [BROKER] - Successfully received the details as: \n" +
								"Topic: " + recordMetadata.topic() + "\n" +
								"Partition: " + recordMetadata.partition() + "\n" +
								"Offset: " + recordMetadata.offset() + "\n" +
								"Timestamp: " + recordMetadata.timestamp()+ "\n");
					} else {
						exception.printStackTrace();
					}
				}
			});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
