package com.lms.lms.transformer;


import com.lms.lms.model.CallFrequency;
import com.lms.lms.model.Restaurant;
import com.lms.lms.model.Status;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.UpdateRestaurantDataRequest;
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
                .status(Status.NEW)
                .build();
    }

    public static void updateRestaurantWithRequest(Restaurant restaurant, UpdateRestaurantDataRequest request) {
        if (StringUtils.hasText(request.getName())) {
            restaurant.setName(restaurant.getName());
        }

        if (StringUtils.hasText(request.getKamId())) {
            restaurant.setKamId(restaurant.getKamId());
        }

        if (StringUtils.hasText(request.getAddress())) {
            restaurant.setAddress(restaurant.getAddress());
        }

        if (request.getStarsRating() != null) {
            restaurant.setStarsRating(request.getStarsRating());
        }

        if (StringUtils.hasText(request.getFrequency())) {
            restaurant.setFrequency(CallFrequency.valueOf(request.getFrequency()));
        }
    }
}
