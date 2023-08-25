package com.hydatis.KycmicroserviceCQRS.command.controller;

import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.command.service.implementation.AgentPpCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pp")
public class AgentPpCommandController {
    @Autowired
    private AgentPpCommandService agentPpQueryService;

    public AgentPersonnePhysique save(AgentPersonnePhysique agentPersonnePhysique){
        try{
            return agentPpQueryService.save(agentPersonnePhysique);
        }catch (Exception e){
            return null;
        }
    }
    public AgentPersonnePhysique update(AgentPersonnePhysique agentPersonnePhysique) {
        try{
            return agentPpQueryService.update(agentPersonnePhysique);
        }catch (Exception e){
            return null;
        }
    }
    public AgentPersonnePhysique delete(Long id) {
        try{
            return agentPpQueryService.delete(id);
        }catch (Exception e){
            return null;
        }
    }
}
