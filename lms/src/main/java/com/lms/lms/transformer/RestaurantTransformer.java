package com.lms.lms.transformer;


import com.lms.lms.model.CallFrequency;
import com.lms.lms.model.Restaurant;
import com.lms.lms.model.Status;
import com.lms.lms.request.AddRestaurantRequest;

import java.util.UUID;

public class RestaurantTransformer {
    public static Restaurant addRestaurantRequestToRestaurant(AddRestaurantRequest request) {
        return Restaurant.builder()
                .id(UUID.randomUUID().toString())
                .kamId(request.getKamId())
                .name(request.getName())
                .address(request.getAddress())
                .starsRating(request.getStarsRating())
                .frequency(CallFrequency.valueOf(request.getFrequency()))
                .status(Status.NEW)
                .build();
    }
}
