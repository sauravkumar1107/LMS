package com.lms.lms.transformer;


import com.lms.lms.entity.CallFrequency;
import com.lms.lms.entity.Restaurant;
import com.lms.lms.entity.RestaurantMetadata;
import com.lms.lms.entity.Status;
import com.lms.lms.request.AddRestaurantRequest;

import java.util.UUID;

public class RestaurantTransformer {
    public static Restaurant addRestaurantRequestToRestaurant(AddRestaurantRequest request) {
        return Restaurant.builder()
                .id(UUID.randomUUID().toString())
                .kamId(request.getKamId())
                .metadata(RestaurantMetadata.builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .starsRating(request.getStarsRating())
                        .build())
                .frequency(CallFrequency.valueOf(request.getFrequency()))
                .status(Status.NEW)
                .build();
    }
}
