package com.fitness.activityservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    public ActivityResponse trackActivity(ActivityRequest activityRequest){
        Boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());
        if(!isValidUser){
            throw new RuntimeException("user not found !"); 
        }
        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }
    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
    public List<ActivityResponse> getUserActivities(String userId){
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
               .map(this::mapToResponse)
               .collect(Collectors.toList());
    }
    public ActivityResponse getActivity(String activityId){
        Activity activity = new Activity();
        activity = activityRepository.findById(activityId)
        .orElseThrow(() -> new RuntimeException("Activity not found! "));
        return mapToResponse(activity);
    }
    // 3:12   
}
