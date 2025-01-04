package com.lms.lms.controller;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.Duration;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("restaurant")
@Tag(name = "Restaurant leads", description = "Lead Management APIs")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Operation(
            summary = "Create a new restaurant lead",
            description = "Creates a new restaurant lead in the system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurant created successfully",
            content = @Content(schema = @Schema(implementation = Restaurant.class))
    )
    @PostMapping("/add")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody AddRestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.addRestaurant(request));
    }

    @GetMapping("/fetch/{kamId}")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@PathVariable("kamId") String kamId) {
        return ResponseEntity.ok(restaurantService.getAllRestaurants(kamId));
    }

    @GetMapping("/{restId}/contacts")
    public ResponseEntity<List<Contact>> getContacts(@PathVariable("restId") String restId) {
        return ResponseEntity.ok(restaurantService.getRestaurantContacts(restId));
    }

    @PostMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurantData(@RequestBody UpdateRestaurantDataRequest request) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(request));
    }

    @GetMapping("/performance/{kamId}/{count}/{order}")
    public ResponseEntity<List<Restaurant>> getPerformers(@PathVariable("kamId") String kamId,
                                      @PathVariable("count") int count,
                                      @PathVariable("order") int inc) {
        return ResponseEntity.ok(restaurantService.getPerformers(kamId, count, inc));
    }

    @GetMapping("/orders/{restId}/{duration}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(@PathVariable("restId") String restId,
                                              @PathVariable("duration") Duration duration) {
        return ResponseEntity.ok(restaurantService.findAllOrders(restId, duration));
    }
}