package com.lms.lms.service;

import com.lms.lms.model.*;
import com.lms.lms.repository.CallRepository;
import com.lms.lms.repository.ContactRepository;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.MakeCallRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.abs;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallerService {
    private final CallRepository callRepository;
    private final RestaurantRepository restaurantRepository;
    private final ContactRepository contactRepository;
    private final OrderRepository orderRepository;
    public boolean makeCall(MakeCallRequest request) {
        try {

            Restaurant restaurant = restaurantRepository.findById(request.getRestId())
                    .orElseThrow(() -> new RuntimeException("Data not found"));

            Contact contact = contactRepository.findById(request.getContactId())
                    .orElseThrow(() -> new RuntimeException("Data not found"));

            Call call = Call.builder()
                    .id(UUID.randomUUID().toString())
                    .restaurant(restaurant)
                    .kamId(request.getKamId())
                    .contact(contact)
                    .callTime(restaurant.getLastCallTime())
                    .build();

            if (StringUtils.hasText(request.getOrderId())) {
                Order order = orderRepository.findById(request.getOrderId())
                        .orElseThrow(() -> new RuntimeException("Data not found"));
                call.setOrder(order);
                restaurant.setStatus(Status.ORDER_PLACED);
            } else {
                restaurant.setStatus(Status.CONTACTED);
            }

            restaurant.setLastCallTime(Instant.now());
            restaurantRepository.save(restaurant);
            callRepository.save(call);
            return true;
        } catch (Exception e) {
            log.error("Encountered error in makeCall", e);
            return false;
        }
    }

    public List<Restaurant> getTodaysScheduledCalls(String kamId) {
        List<Restaurant> restaurantList = restaurantRepository.findByKamId(kamId);
        restaurantList.removeIf(restaurant -> {
            if (abs(Duration.between(Instant.now(), restaurant.nextScheduledCall()).toHours()) <= 12) {
                return false;
            }
            return true;
        });

        return restaurantList;
    }
}
