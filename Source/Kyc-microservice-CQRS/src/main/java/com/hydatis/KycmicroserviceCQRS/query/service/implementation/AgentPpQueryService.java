package com.hydatis.KycmicroserviceCQRS.query.service.implementation;


import com.hydatis.KycmicroserviceCQRS.query.Repository.AgentPpMongoRepo;
import com.hydatis.KycmicroserviceCQRS.query.document.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.query.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentPpQueryService implements QueryService<AgentPersonnePhysique> {
    @Autowired
    private AgentPpMongoRepo agentPpMongoRepo;
    @Override
    public AgentPersonnePhysique findOneById(Long id) {
        Optional<AgentPersonnePhysique> agentPersonnePhysique = agentPpMongoRepo.findById(id);
        if(agentPersonnePhysique.isPresent()){
            return agentPersonnePhysique.get();
        }else return null;
    }

    @Override
    public List<AgentPersonnePhysique> findAll() {
        return agentPpMongoRepo.findAll();
    }
}
