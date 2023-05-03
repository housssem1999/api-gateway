package org.microservices.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakToken {
    String access_token;
    String refresh_token;
    String token_type;
    int expires_in;
    int refresh_expires_in;
    String scope;
    String session_state;
    int not_before_policy;
}
