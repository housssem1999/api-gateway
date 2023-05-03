package org.microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class KeycloakRequest {
    String grant_type;
    String client_id;
    String client_secret;
    String username;
    String password;

}

