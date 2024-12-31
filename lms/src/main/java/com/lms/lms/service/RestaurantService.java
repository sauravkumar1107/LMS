package com.lms.lms.service;

import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.transformer.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    public boolean addRestaurant(AddRestaurantRequest request) {
        Restaurant restaurant = RestaurantTransformer.addRestaurantRequestToRestaurant(request);
        repository.save(restaurant);
        return true;
    }

    public List<Restaurant> getAllRestaurants(String kamId) {
        return repository.findByKamId(kamId);
    }
}
