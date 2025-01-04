package com.lms.lms.service;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.ContactRepository;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.Period;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.transformer.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ContactRepository contactRepository;
    private final OrderRepository orderRepository;

    public Restaurant addRestaurant(AddRestaurantRequest request) {
        Restaurant restaurant = RestaurantTransformer.addRestaurantRequestToRestaurant(request);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants(String kamId) {
        return restaurantRepository.findByKamId(kamId);
    }

    public List<Contact> getRestaurantContacts(String restId) {
        return contactRepository.findByRestId(restId);
    }

    public Restaurant updateRestaurant(UpdateRestaurantDataRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Data not found"));
        RestaurantTransformer.updateRestaurantWithRequest(restaurant, request);
        return restaurantRepository.save(restaurant);
    }

    public List<Order> findAllOrders(String restId, Period period) {
        int days = switch (period) {
            case DAY -> 1;
            case WEEK -> 7;
            case MONTH -> 30;
            case YEAR -> 3665;
            default -> throw new UnsupportedOperationException("Duration is not supported");
        };

        List<Order> orders = orderRepository.findAllByRestaurantIdAndOrderDateAfter(restId, Instant.now().minus(days, ChronoUnit.DAYS));

//        orders.removeIf(order -> {
//            if (java.time.Duration.between(order.getOrderDate(), Instant.now()).toDays() <= days) {
//                return false;
//            }
//            return true;
//        });

        return orders;
    }

    public List<Restaurant> getPerformers(String kamId, int count, int inc) {
        List<Restaurant> restaurants = restaurantRepository.findByKamId(kamId);
        return restaurants;
    }
}
