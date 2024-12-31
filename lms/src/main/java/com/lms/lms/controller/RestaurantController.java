package com.lms.lms.controller;

import com.lms.lms.model.Restaurant;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.Duration;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/add")
    public boolean addRestaurant(@RequestBody AddRestaurantRequest request) {
        return restaurantService.addRestaurant(request);
    }

    @GetMapping("/fetch/{kamId}")
    public List<Restaurant> getAllRestaurants(@PathVariable("kamId") String kamId) {
        return restaurantService.getAllRestaurants(kamId);
    }

    @GetMapping("/contacts/{restId}")
    public String getContacts(@PathVariable("restId") String restId) {
        return "testphone";
    }

    @PostMapping("/update")
    public void updateRestaurantData(@RequestBody UpdateRestaurantDataRequest request) {

    }

    @GetMapping("/performance/{kamId}/{count}/{order}")
    public List<String> getPerformers(@PathVariable("kamId") String kamId,
                                      @PathVariable("count") int count,
                                      @PathVariable("order") int order) {
        return null;
    }

    @GetMapping("/orders/{restId}/{duration}")
    public List<String> getAllRestaurantOrders(@PathVariable("restId") String restId,
                                               @PathVariable("duration") Duration duration) {
        return null;
    }
}