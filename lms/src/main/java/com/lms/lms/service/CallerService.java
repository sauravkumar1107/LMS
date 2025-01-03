package com.lms.lms.service;

import com.lms.lms.model.Call;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.CallRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.MakeCallRequest;
import com.lms.lms.transformer.CallTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.abs;

@Service
@RequiredArgsConstructor
public class CallerService {
    private final CallRepository callRepository;
    private final RestaurantRepository restaurantRepository;
    public boolean makeCall(MakeCallRequest request) {
        Call call = CallTransformer.makeCallRequestToCallModel(request);
        callRepository.save(call);

        Restaurant restaurant = restaurantRepository.findById(request.getRestId()).orElseThrow(() -> new RuntimeException("Data not found"));
        restaurant.setLastCallId(call.getCallId());
        restaurant.setLastCallTime(call.getCallTime());
        restaurantRepository.save(restaurant);
        return true;
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
