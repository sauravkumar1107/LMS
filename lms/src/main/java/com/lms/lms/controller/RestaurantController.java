package com.lms.lms.controller;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.Duration;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.service.OrderService;
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
    private final OrderService orderService;

    @PostMapping("/add")
    public boolean addRestaurant(@RequestBody AddRestaurantRequest request) {
        return restaurantService.addRestaurant(request);
    }

    @GetMapping("/fetch/{kamId}")
    public List<Restaurant> getAllRestaurants(@PathVariable("kamId") String kamId) {
        return restaurantService.getAllRestaurants(kamId);
    }

    @GetMapping("/{restId}/contacts")
    public List<Contact> getContacts(@PathVariable("restId") String restId) {
        return restaurantService.getRestaurantContacts(restId);
    }

    @PostMapping("/update")
    public boolean updateRestaurantData(@RequestBody UpdateRestaurantDataRequest request) {
        return restaurantService.updateRestaurant(request);
    }

    @GetMapping("/performance/{kamId}/{count}/{order}")
    public List<String> getPerformers(@PathVariable("kamId") String kamId,
                                      @PathVariable("count") int count,
                                      @PathVariable("order") int order) {
        return null;
    }

    @GetMapping("/orders/{restId}/{duration}")
    public List<Order> getAllRestaurantOrders(@PathVariable("restId") String restId,
                                              @PathVariable("duration") Duration duration) {
        return orderService.findAllOrders(restId, duration);
    }
}