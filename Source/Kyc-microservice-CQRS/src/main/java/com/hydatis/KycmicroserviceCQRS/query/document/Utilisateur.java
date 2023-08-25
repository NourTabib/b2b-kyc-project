package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public abstract class Utilisateur {
    private String username;
    private String password;
    private String email;
}
