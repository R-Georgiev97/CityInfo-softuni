package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.Rating;
import com.example.cityinfo.model.entity.User;
import com.example.cityinfo.repository.RatingRepository;
import com.example.cityinfo.service.RatingService;
import com.example.cityinfo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;

    public RatingServiceImpl(RatingRepository ratingRepository, UserService userService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    @Override
    public void store(Integer rate, Object object) {
        String username = "";
        java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        Rating rating = new Rating();
        rating.setRating(rate);
        rating.setObject(object);
        rating.setUser(userService.getByUsername(username));
        ratingRepository.save(rating);
    }

    @Override
    public Boolean userAlreadyVotedForObject(Object object) {
        String username = "";
        java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        User user = userService.getByUsername(username);

        return ratingRepository.existsByObjectAndUser(object,user);
    }
}
