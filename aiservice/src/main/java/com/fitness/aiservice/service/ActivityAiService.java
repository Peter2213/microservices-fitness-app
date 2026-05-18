package com.fitness.aiservice.service;

import org.springframework.stereotype.Service;

import com.fitness.aiservice.model.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAiService {
    private final GeminiService geminiService;

    public String generateRecommendation(Activity activity){
        String prompt = ActivityPrompt(activity);
        String response = geminiService.getAnswer(prompt);
        log.info("response from Ai is: {}", response);
        return response;
    }
    private String ActivityPrompt(Activity activity){
        return String.format(
            "Please analyze the following fitness activity and provide detailed recommendations:\n\n" +
            "Activity Type: %s\n" +
            "Duration: %d minutes\n" +
            "Calories Burned: %d\n" +
            "Start Time: %s\n" +
            "Additional Metrics: %s\n\n" +
            "Based on this activity, please provide:\n" +
            "1. Performance Analysis: Evaluate the intensity and effectiveness of the workout\n" +
            "2. Detailed Recommendations: Suggest improvements for better results\n" +
            "3. Next Steps: Recommend follow-up activities or training focus areas\n" +
            "4. Safety Tips: Any important safety considerations or recovery advice\n" +
            "5. Motivation: Provide encouraging feedback on the achievement\n\n" +
            "Please be specific and actionable in your recommendations.",
            activity.getType(),
            activity.getDuration() != null ? activity.getDuration() : 0,
            activity.getCaloriesBurned() != null ? activity.getCaloriesBurned() : 0,
            activity.getStartTime() != null ? activity.getStartTime() : "Not recorded",
            activity.getAdditionalMetrics() != null ? activity.getAdditionalMetrics().toString() : "None"
        );
    }
}
