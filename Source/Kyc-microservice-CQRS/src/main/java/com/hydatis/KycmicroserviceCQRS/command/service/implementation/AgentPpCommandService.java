package com.hydatis.KycmicroserviceCQRS.command.service.implementation;

import com.hydatis.KycmicroserviceCQRS.command.eventhandler.implementaion.AgentPersonnePhysiqueEventHandler;
import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.command.repository.AgentPPRepository;
import com.hydatis.KycmicroserviceCQRS.command.service.CommandService;
import com.hydatis.KycmicroserviceCQRS.events.CreateEvent;
import com.hydatis.KycmicroserviceCQRS.events.DeleteEvent;
import com.hydatis.KycmicroserviceCQRS.events.Event;
import com.hydatis.KycmicroserviceCQRS.events.UpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class AgentPpCommandService implements CommandService<AgentPersonnePhysique> {

    private final AgentPPRepository agentPpRepository;
    private final AgentPersonnePhysiqueEventHandler eventHandler;

    @Autowired
    public AgentPpCommandService(AgentPPRepository agentPpRepository, AgentPersonnePhysiqueEventHandler eventHandler) {
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
        try{
            AgentPersonnePhysique agentPersonnePhysique = agentPpRepository.save(entity);
            Event<AgentPersonnePhysique> createEvent = new CreateEvent<>(agentPersonnePhysique);
            eventHandler.publish(createEvent);
            return agentPersonnePhysique;
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
