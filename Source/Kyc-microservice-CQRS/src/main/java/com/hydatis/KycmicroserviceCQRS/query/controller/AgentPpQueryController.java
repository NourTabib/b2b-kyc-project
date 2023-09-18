package com.hydatis.KycmicroserviceCQRS.query.controller;

import com.hydatis.KycmicroserviceCQRS.query.document.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.query.service.implementation.AgentPpQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pp")
public class AgentPpQueryController {

    private final AgentPpQueryService agentPpQueryService;

    @Autowired
    public AgentPpQueryController(AgentPpQueryService agentPpQueryService){
        this.agentPpQueryService = agentPpQueryService;
    }


    public AgentPersonnePhysique findOneById(Long id){
        return this.agentPpQueryService.findOneById(id);
    }
    public List<AgentPersonnePhysique> findAll(){
        return this.agentPpQueryService.findAll();
    }
}
