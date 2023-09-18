package com.hydatis.KycmicroserviceCQRS.command.controller;

import com.hydatis.KycmicroserviceCQRS.command.dto.AgentPersonnePhysiqueDTO;
import com.hydatis.KycmicroserviceCQRS.command.model.*;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.*;

import com.hydatis.KycmicroserviceCQRS.command.service.implementation.AgentPpCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class AgentPpCommandController {

    private final AgentPpCommandService agentPpCommandService;

    @Autowired
    public AgentPpCommandController(AgentPpCommandService agentPpCommandService) {
        this.agentPpCommandService = agentPpCommandService;
    }
    @PostMapping(path = "/formpp")
    public ResponseEntity<AgentPersonnePhysique> saveAgent(@Valid @RequestBody AgentPersonnePhysiqueDTO agentDTO) {

        AgentPersonnePhysique agentPersonnePhysique = convertDTOToEntity(agentDTO);

        AgentPersonnePhysique savedAgent = agentPpCommandService.save(agentPersonnePhysique);

        if (savedAgent != null) {
            return ResponseEntity.ok(savedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(path = "/formpp")
    public ResponseEntity<List<AgentPersonnePhysique>> findAll() {
        // Use the AgentPpCommandService or appropriate service to retrieve all agents
        List<AgentPersonnePhysique> agents = agentPpCommandService.findAll();

        if (!agents.isEmpty()) {
            return ResponseEntity.ok(agents);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    private AgentPersonnePhysique convertDTOToEntity(AgentPersonnePhysiqueDTO agentDTO) {
        System.out.println(agentDTO.toString());
        AgentPersonnePhysique agent = new AgentPersonnePhysique();

        try{
            agent.setNom(agentDTO.getStep1().getLastName());
            agent.setPrenom(agentDTO.getStep1().getFirstName());
            agent.setDateDeNaissance(agentDTO.getStep1().getDdn());
            agent.setAdressePerso(agentDTO.getStep1().getAdressePersonnelle());
            agent.setAddresseCourier(agentDTO.getStep1().getAdresseCourrier());
            agent.setTelephone(agentDTO.getStep1().getPhone());
            agent.setEmail(agentDTO.getStep1().getAdresseEmail());
            if (agentDTO.getStep1().isPPE()){
                agent.setEstPPE(true);
                agent.setFonctionPpe(agentDTO.getStep1().getFonction());
            }else{
                agent.setEstPPE(false);
            }
            if (agentDTO.getStep1().isPPI()){
                agent.setEstPPI(true);
                agent.setPays(agentDTO.getStep1().getPays());
            }else{
                agent.setEstPPI(false);
            }
        }catch (Exception e ){
            System.out.println(e.toString());
        }

        try{
            Document document = new Document();
            document.setIdDoc(agentDTO.getStep1().getDocid());
            document.setTypeDocument(TypeDocument.valueOf(agentDTO.getStep1().getDocumentType()));
            document.setDateEmission(agentDTO.getStep1().getDateEmission());
            document.setLieuEmission(agentDTO.getStep1().getLieuEmission());
            agent.setDocument(document);
        }catch(Exception e){
            System.out.println(e.toString());
        }


        try{
            Compte compte = new Compte();

            compte.setRaison(agentDTO.getStep1().getRaison());

            compte.setAutreSourceAlimentationValue(agentDTO.getStep2().getAutreMoyenPaiementText());

            if (agentDTO.getStep2().isVirementespeces()) {
                compte.setSourceAlimentation(SourceAlimentation.VERSEMENT_ESPECE);
            }
            if (agentDTO.getStep2().isRevenusPro()) {
                compte.setSourceAlimentation(SourceAlimentation.REVENUS_PROFESSIONNELS);
            }
            if (agentDTO.getStep2().isProduitInvestissement()) {
                compte.setSourceAlimentation(SourceAlimentation.PRODUIT_INVESTISSEMENTS);
            }
            if (agentDTO.getStep2().isAutresource()) {
                compte.setSourceAlimentation(SourceAlimentation.AUTRE);
                compte.setAutreSourceAlimentationValue(agentDTO.getStep2().getAutreSourceText());
            } else {
                compte.setAutreSourceAlimentationValue(null);
            }
            compte.setTypeDeCompte(mapComptePaiement(agentDTO.getStep2().getComptePaiement()));
            // Create operationDebit
            Operation operationDebit = new Operation();
            operationDebit.setMontantMoyenHebdo(agentDTO.getStep2().getMontantsHebdomadairesdebit());
            operationDebit.setMontantMoyenMensuels(agentDTO.getStep2().getMontantsMensuelsdebit());
            operationDebit.setMontantMoyenJournaliers(Double.valueOf(agentDTO.getStep2().getMontantsJournaliersdebit()));

            operationDebit.setCash(agentDTO.getStep2().isCash());

            operationDebit.setVirement(agentDTO.getStep2().isVirementdebit());



            if (agentDTO.getStep2().isAutreMoyendebit()) {
                operationDebit.setAutreMoyen(agentDTO.getStep2().getAutreMoyendebitText());
            }
            operationDebit.setTypeOperation(TypeOperation.DEBIT);
//        operationDebit.setCompte(compte);
            // Create operationCredit
            Operation operationCredit = new Operation();
            operationCredit.setMontantMoyenHebdo(agentDTO.getStep2().getMontantsHebdomadaires());
            operationCredit.setMontantMoyenMensuels(agentDTO.getStep2().getMontantsMensuels());
            operationCredit.setMontantMoyenJournaliers(agentDTO.getStep2().getMontantsJournaliers());

            operationCredit.setVirementInterne(agentDTO.getStep2().isVirementInterne());

            operationCredit.setVirementBancairePostaux(agentDTO.getStep2().isVirementBancaire());
//        operationCredit.setCompte(compte);

            if (agentDTO.getStep2().isAutreMoyenPaiement()) {
                operationCredit.setAutreMoyen(agentDTO.getStep2().getAutreMoyenPaiementText());
            }
            operationCredit.setTypeOperation(TypeOperation.CREDIT);

            compte.setOperationDebit(operationDebit);
            compte.setOperationCredit(operationCredit);
            agent.setCompte(compte);

            // Map properties from Step3


            if (agentDTO.getStep3() != null && agentDTO.getStep3().getBanks() != null) {
                List<Banque> banques = agentDTO.getStep3().getBanks().stream()
                        .map(dtoBank -> {
                            Banque banque = new Banque();
                            banque.setNomBanque(dtoBank.getBanque());
                            banque.setAdresse(dtoBank.getAdressebanque());
                            banque.setSwift((dtoBank.getCode()));
                            banque.setRib(dtoBank.getNumCompte());

                            return banque;
                        })
                        .collect(Collectors.toList());
                agent.setBanqueEnRelation(banques);
            }
            if (!agentDTO.getStep2().isBeneficaire()) {
                AgentPersonnePhysique beneficiaireEffectif = new AgentPersonnePhysique();
                beneficiaireEffectif.setTitulaireDuCompte(agent);
                beneficiaireEffectif.setNom(agentDTO.getStep2().getLastNameBeneficaire());
                beneficiaireEffectif.setPrenom(agentDTO.getStep2().getFirstNameBeneficaire());
                Document documentBeneficiaire = new Document();
                documentBeneficiaire.setIdDoc(Long.valueOf(agentDTO.getStep2().getDocIdBeneficaire()));
                documentBeneficiaire.setTypeDocument(TypeDocument.valueOf(agentDTO.getStep2().getDocumentTypeBeneficaire()));
                documentBeneficiaire.setDateEmission(agentDTO.getStep2().getDateEmissionBeneficaire());
                documentBeneficiaire.setLieuEmission(agentDTO.getStep2().getLieuEmissionBeneficaire());
                documentBeneficiaire.setDateExpiration(agentDTO.getStep2().getDateExpirationBeneficaire());

                beneficiaireEffectif.setDocument(documentBeneficiaire);

                beneficiaireEffectif.setDateDeNaissance((agentDTO.getStep2().getDdnBeneficaire()));
                beneficiaireEffectif.setNationalite(agentDTO.getStep2().getNationaliteBeneficaire());
                beneficiaireEffectif.setEstResident(agentDTO.getStep2().isResidentBeneficaire());
                beneficiaireEffectif.setResidence(agentDTO.getStep2().getPaysResidenceBeneficaire());


                if (agentDTO.getStep2().isPPEBeneficaire()) {
                    beneficiaireEffectif.setEstPPE(true);
                    beneficiaireEffectif.setFonctionPpe(agentDTO.getStep2().getFonctionBeneficaire());
                }else{
                    beneficiaireEffectif.setEstPPE(false);
                }
                agent.setBeneficiaireEffectif(beneficiaireEffectif);
            }else{
                agent.setEstBeneficiareEffectifs(true);
                agent.setBeneficiaireEffectif(null);
            }
            CategorieSocioProfesionnelle categorieSocioProfesionnelle = new CategorieSocioProfesionnelle();
            categorieSocioProfesionnelle.setTypeAgent(TypeAgent.valueOf(agentDTO.getStep1().getAgentType()));
            categorieSocioProfesionnelle.setRaisonSociale(agentDTO.getStep1().getEmployeur());
            categorieSocioProfesionnelle.setAdresseProfessionelle(agentDTO.getStep1().getAdressePro());
            categorieSocioProfesionnelle.setTelephone(agentDTO.getStep1().getTelFaxPro());
            categorieSocioProfesionnelle.setEmail(agentDTO.getStep1().getEmailPro());
            categorieSocioProfesionnelle.setTypeActivite(agentDTO.getStep1().getTypeActivite());
            categorieSocioProfesionnelle.setZoneGeo(agentDTO.getStep1().getZoneActivite());
            agent.setCategorieSocioProfesionnelle(categorieSocioProfesionnelle);
        }catch (Exception e){
            System.out.println(e.toString());
        }



        DemandeEngagement demandeEngagement = new DemandeEngagement();
        demandeEngagement.setDateDemande(LocalDateTime.now());
        demandeEngagement.setSignatureAgent(agentDTO.getStep4().getNomPrenomAgent());

        agent.setDemandeEngagement(demandeEngagement);

        return agent;
    }
    public static EtatDeCompte mapComptePaiement(String comptePaiement){
        switch (comptePaiement) {
            case "Niveau 1":
                return EtatDeCompte.NIVEAU_1;
            case "Niveau 2":
                return EtatDeCompte.NIVEAU_2;
            case "Niveau 3":
                return EtatDeCompte.NIVEAU_3;
            default:
                return null;
        }
    }




    @GetMapping("/all")
    public ResponseEntity<List<AgentPersonnePhysique>> getAllAgents() {
        List<AgentPersonnePhysique> agents = agentPpCommandService.findAll();
        return ResponseEntity.ok(agents);
    }



    @PutMapping("/update")
    public ResponseEntity<AgentPersonnePhysique> updateAgent(@RequestBody AgentPersonnePhysique agentPersonnePhysique) {
        AgentPersonnePhysique updatedAgent = agentPpCommandService.update(agentPersonnePhysique);
        if (updatedAgent != null) {
            return ResponseEntity.ok(updatedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AgentPersonnePhysique> deleteAgent(@PathVariable("id") Long id) {
        AgentPersonnePhysique deletedAgent = agentPpCommandService.delete(id);
        if (deletedAgent != null) {
            return ResponseEntity.ok(deletedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
