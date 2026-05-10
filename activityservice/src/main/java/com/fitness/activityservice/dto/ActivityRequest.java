package com.fitness.activityservice.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class ActivityRequest {
    private String userId;
    private String type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
}
