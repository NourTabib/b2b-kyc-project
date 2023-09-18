package com.hydatis.KycmicroserviceCQRS.command.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import   com.hydatis.KycmicroserviceCQRS.command.eventhandler.implementaion.AgentPersonnePhysiqueEventHandler;
import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.command.model.CategorieSocioProfesionnelle;
import com.hydatis.KycmicroserviceCQRS.command.model.Compte;
import com.hydatis.KycmicroserviceCQRS.command.model.Document;
import com.hydatis.KycmicroserviceCQRS.command.repository.AgentPPRepository;
import com.hydatis.KycmicroserviceCQRS.command.repository.CategorieSocioProfesionnelleRepository;
import com.hydatis.KycmicroserviceCQRS.command.repository.CompteRepository;
import com.hydatis.KycmicroserviceCQRS.command.repository.DocumentRepository;
import com.hydatis.KycmicroserviceCQRS.command.service.CommandService;
import com.hydatis.KycmicroserviceCQRS.events.CreateEvent;
import com.hydatis.KycmicroserviceCQRS.events.DeleteEvent;
import com.hydatis.KycmicroserviceCQRS.events.Event;
import com.hydatis.KycmicroserviceCQRS.events.UpdateEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Slf4j
@Transactional
@org.springframework.stereotype.Service
public class AgentPpCommandService implements CommandService<AgentPersonnePhysique> {

    private final AgentPPRepository agentPpRepository;
    private final ObjectMapper objectMapper ;
    private final AgentPersonnePhysiqueEventHandler eventHandler;
    @Autowired
    public AgentPpCommandService(AgentPPRepository agentPpRepository,AgentPersonnePhysiqueEventHandler eventHandler){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.agentPpRepository = agentPpRepository;
        this.eventHandler = eventHandler;
    }


    @Override
    public AgentPersonnePhysique findOneById(Long id) {
        Optional<AgentPersonnePhysique> agentPersonnePhysique = agentPpRepository.findById(id);
        if(agentPersonnePhysique.isPresent()) return agentPersonnePhysique.get();
        else return null;
    }

    @Override
    public List<AgentPersonnePhysique> findAll() {
        return agentPpRepository.findAll();
    }

    @Override

    public AgentPersonnePhysique save(AgentPersonnePhysique entity) {
        try {
            AgentPersonnePhysique agentPersonnePhysique = agentPpRepository.save(entity);
            agentPersonnePhysique.getCategorieSocioProfesionnelle();
            agentPersonnePhysique.getCompte();
            System.out.println("in command saved AgentPp" + objectMapper.writeValueAsString(entity));
            Event<AgentPersonnePhysique> createEvent = new CreateEvent<>(agentPersonnePhysique);
            eventHandler.publish(createEvent);

            return agentPersonnePhysique;
        } catch (Exception e) {
            // Rollback the transaction and throw a runtime exception with a custom message.
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("Failed to save the agent: " + e.getMessage(), e);
        }
    }



    @Override
    public AgentPersonnePhysique update(AgentPersonnePhysique entity) {
        try{
            if(agentPpRepository.findById(entity.getId()).isPresent()) {
                AgentPersonnePhysique agentPersonnePhysique = agentPpRepository.save(entity);
                Event<AgentPersonnePhysique> updateEvent = new UpdateEvent<>(agentPersonnePhysique);
                eventHandler.publish(updateEvent);
                return agentPersonnePhysique;
            }
            else return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AgentPersonnePhysique delete(Long id) {
        try{
            Optional<AgentPersonnePhysique> agentPersonnePhysique = agentPpRepository.findById(id);
            if (agentPersonnePhysique.isPresent()) {
                agentPpRepository.deleteById(id);
                Event<AgentPersonnePhysique> deleteEvent = new DeleteEvent<>(agentPersonnePhysique.get());
                eventHandler.publish(deleteEvent);
                return agentPersonnePhysique.get();
            } else return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}