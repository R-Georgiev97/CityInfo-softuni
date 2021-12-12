package com.example.cityinfo.scheduler;

import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.Rating;
import com.example.cityinfo.repository.ObjectRepository;
import com.example.cityinfo.repository.RatingRepository;
import com.example.cityinfo.service.ObjectService;
import com.example.cityinfo.service.RatingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculateAverageRatingScheduler {

    private final ObjectService objectService;
    private final RatingService ratingService;
    private final ObjectRepository objectRepository;

    public CalculateAverageRatingScheduler(ObjectService objectService, RatingService ratingService, ObjectRepository objectRepository) {
        this.objectService = objectService;
        this.ratingService = ratingService;
        this.objectRepository = objectRepository;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000, initialDelay = 10 * 60 * 1000)
    public void calculateAverageRating() {
        System.out.println("schechule");
        List<Object> objects = objectService.getAllApprovedRaw();
        objects.forEach(object -> {
            Double averageRating = calculate(object);
            object.setAverageRating(averageRating);
            objectRepository.save(object);
        });
    }

    private Double calculate(Object object) {
        double average;
        List<Rating> ratings = ratingService.getAllByObject(object);
        average = ratings.stream().mapToDouble(Rating::getRating).sum();

        return average;
    }
}
