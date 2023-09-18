package com.hydatis.KycmicroserviceCQRS.command.repository;

import com.hydatis.KycmicroserviceCQRS.command.model.CategorieSocioProfesionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieSocioProfesionnelleRepository extends JpaRepository<CategorieSocioProfesionnelle, Long>  {
}
