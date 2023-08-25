package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User  {
    private String username;
    private String password;
    private String email;
    private String role;


}


