package com.hydatis.KycmicroserviceCQRS.command.repository;

import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentPPRepository extends JpaRepository<AgentPersonnePhysique,Long> {

}
