package com.lms.lms.service;

import com.lms.lms.entity.Restaurant;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.transformer.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    public boolean addRestaurant(AddRestaurantRequest request) {
        Restaurant restaurant = RestaurantTransformer.addRestaurantRequestToRestaurant(request);
//        repository
        return true;
    }
}
