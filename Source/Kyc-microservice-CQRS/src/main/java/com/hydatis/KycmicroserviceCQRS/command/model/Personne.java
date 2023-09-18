package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Personne {

    @Column
    private String nationalite;
    @Column
    private String residence;
    @Column
    private boolean estResident;
    @Column
    private Long telephone;
    @Column
    private String email;

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setEstResident(boolean estResident) {
        this.estResident = estResident;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getResidence() {
        return residence;
    }

    public boolean isEstResident() {
        return estResident;
    }

    public Long getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }
}