package com.lms.transformer;


import com.lms.model.CallFrequency;
import com.lms.model.Restaurant;
import com.lms.model.Status;
import com.lms.request.AddRestaurantRequest;
import com.lms.request.UpdateRestaurantDataRequest;
import org.springframework.util.StringUtils;

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
                .status(Status.ACTIVE)
                .build();
    }

    public static void updateRestaurantWithRequest(Restaurant restaurant, UpdateRestaurantDataRequest request) {
        if (StringUtils.hasText(request.getName())) {
            restaurant.setName(request.getName());
        }

        if (StringUtils.hasText(request.getKamId())) {
            restaurant.setKamId(request.getKamId());
        }

        if (StringUtils.hasText(request.getAddress())) {
            restaurant.setAddress(request.getAddress());
        }

        if (request.getStarsRating() != null) {
            restaurant.setStarsRating(request.getStarsRating());
        }

        if (StringUtils.hasText(request.getFrequency())) {
            restaurant.setFrequency(CallFrequency.valueOf(request.getFrequency()));
        }

        if (StringUtils.hasText(request.getStatus())) {
            restaurant.setStatus(Status.valueOf(request.getStatus()));
        }
    }
}
