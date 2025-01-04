package com.lms.lms.service;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.ContactRepository;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.Duration;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.transformer.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ContactRepository contactRepository;
    private final OrderRepository orderRepository;

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
        try {
            Restaurant restaurant = restaurantRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Data not found"));
            RestaurantTransformer.updateRestaurantWithRequest(restaurant, request);
            restaurantRepository.save(restaurant);
            return true;
        } catch (Exception e) {
            log.error("Encountered error in updateRestaurant", e);
            return false;
        }
    }

    public List<Order> findAllOrders(String restId, Duration duration) {
        List<Order> orders = orderRepository.findByRestId(restId);
        int days = switch (duration) {
            case DAY -> 1;
            case WEEK -> 7;
            case MONTH -> 30;
            case YEAR -> 3665;
            default -> throw new UnsupportedOperationException("Duration is not supported");
        };

        orders.removeIf(order -> {
            if (java.time.Duration.between(order.getOrderDate(), Instant.now()).toDays() <= days) {
                return false;
            }
            return true;
        });

        return orders;
    }

    public List<Restaurant> getPerformers(String kamId, int count, int inc) {
        List<Restaurant> restaurants = restaurantRepository.findByKamId(kamId);
        return restaurants;
    }
}
