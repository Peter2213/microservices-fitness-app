package com.fitness.aiservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitness.aiservice.model.Recommendations;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendations, String>{

    List<Recommendations> findByUserId(String userId);

     Optional<Recommendations> findByActivityId(String activityId);

}
