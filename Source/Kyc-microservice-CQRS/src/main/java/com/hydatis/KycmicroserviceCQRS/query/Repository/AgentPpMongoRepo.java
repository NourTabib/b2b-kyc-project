package com.hydatis.KycmicroserviceCQRS.query.Repository;

import com.hydatis.KycmicroserviceCQRS.query.document.AgentPersonnePhysique;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AgentPpMongoRepo extends MongoRepository<AgentPersonnePhysique,String> {
    Optional<AgentPersonnePhysique> findById(Long id);
}
