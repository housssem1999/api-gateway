package org.microservices.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    String username;
    String password;
}
