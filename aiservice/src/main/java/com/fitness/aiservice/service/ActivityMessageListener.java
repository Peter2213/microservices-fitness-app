package com.fitness.aiservice.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fitness.aiservice.model.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {
    private final ActivityAiService activityAiService;
    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity){
        log.info("recieved activity for processing {} ", activity.getId());
        log.info("generating a recommendation for the pushed activity", activityAiService.generateRecommendation(activity));
    }
}
