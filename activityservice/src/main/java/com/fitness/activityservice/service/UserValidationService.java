package com.fitness.activityservice.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public Boolean validateUser(String userId){
        log.info("Calling User Validation API for userId: {}", userId);
        try{
        return userServiceWebClient.get()
            .uri("/api/users/{userId}/validate", userId)
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();
        }
        catch(WebClientResponseException e){
            if(e.getStatusCode().value() == 404){
                throw new RuntimeException("User Not Found: " + userId);
            }
            else if(e.getStatusCode().value() == 400){
                throw new RuntimeException("User Not Found: " + userId);
                // 3:18
            }
        }
        return false;
    }
}
