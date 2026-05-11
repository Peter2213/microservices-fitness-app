package com.fitness.aiservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.aiservice.model.Recommendations;
import com.fitness.aiservice.service.RecommendationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recommendations")
@AllArgsConstructor
public class RecommendationController {
    private RecommendationService recommendationService;
    @GetMapping("user/{userId}")
    public ResponseEntity<List<Recommendations>> getUserRecommendations(@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getUserRecommendations(userId));
    }
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendations> getActivityRecommendations(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendations(activityId));
    }
}
