package com.lms.lms.service;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.ContactRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.transformer.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ContactRepository contactRepository;
    public boolean addRestaurant(AddRestaurantRequest request) {
        Restaurant restaurant = RestaurantTransformer.addRestaurantRequestToRestaurant(request);
        restaurantRepository.save(restaurant);
        return true;
    }

    public List<Restaurant> getAllRestaurants(String kamId) {
        return restaurantRepository.findByKamId(kamId);
    }

    public List<Contact> getRestaurantContacts(String restId) {
        return contactRepository.findByRestId(restId);
    }

    public boolean updateRestaurant(UpdateRestaurantDataRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Data not found"));
        RestaurantTransformer.updateRestaurantWithRequest(restaurant, request);
        restaurantRepository.save(restaurant);
        return true;
    }
}
