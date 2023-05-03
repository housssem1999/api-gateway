package org.microservices.controller;


import org.microservices.dto.KeycloakToken;
import org.microservices.dto.LoginRequest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class Controller {


    @GetMapping("/user")
    public String index(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/product")
    @RolesAllowed({"ADMIN"})
    public String getProduct(Principal principal) {
        return "Response from Product Service, User Id:" + principal.getName();
    }
    @PostMapping("/login")
    public ResponseEntity<KeycloakToken> login(@RequestBody LoginRequest loginRequest) {

        String url = "http://localhost:8180/realms/image-annotation-realm/protocol/openid-connect/token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", "image-annotation");
        requestBody.add("client_secret", "6REOoxe0KcivJUxcpvdbEBlMCMLnsIMR");
        requestBody.add("username", loginRequest.getUsername());
        requestBody.add("password", loginRequest.getPassword());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<KeycloakToken> response = restTemplate.postForEntity(url, request, KeycloakToken.class);
        return ResponseEntity.ok(response.getBody());
    }

}