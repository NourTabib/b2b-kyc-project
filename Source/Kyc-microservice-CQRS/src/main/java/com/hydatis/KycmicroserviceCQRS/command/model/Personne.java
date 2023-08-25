package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Personne {
    private String nationalite;
    private String residence;
    private Boolean estResident;
    private String telephone;
    private String email;
}