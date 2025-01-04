package com.lms.lms.controller;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.Period;
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

    @Operation(
            description = "Fetch all restaurant leads created by a KAM"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurants fetched"
    )
    @GetMapping("/fetch/{kamId}")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@PathVariable("kamId") String kamId) {
        return ResponseEntity.ok(restaurantService.getAllRestaurants(kamId));
    }

    @Operation(
            description = "Fetch all contacts for a restaurant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Contacts fetched"
    )
    @GetMapping("/{restId}/contacts")
    public ResponseEntity<List<Contact>> getContacts(@PathVariable("restId") String restId) {
        return ResponseEntity.ok(restaurantService.getRestaurantContacts(restId));
    }

    @Operation(
            description = "Update details of a restaurant in the system, details like KAM, name, address, status, etc"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Details updated"
    )
    @PostMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurantData(@RequestBody UpdateRestaurantDataRequest request) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(request));
    }

    @Operation(
            description = "Find the top or low performers for a KAM"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Details fetched"
    )
    @GetMapping("/performance/{kamId}/{count}/{order}")
    public ResponseEntity<List<Restaurant>> getPerformers(@PathVariable("kamId") String kamId,
                                      @PathVariable("count") int count,
                                      @PathVariable("order") int inc) {
        return ResponseEntity.ok(restaurantService.getPerformers(kamId, count, inc));
    }

    @Operation(
            description = "Find all orders of a restaurant in a given period"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders fetched"
    )
    @GetMapping("/orders/{restId}/{period}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(@PathVariable("restId") String restId,
                                              @PathVariable("duration") Period period) {
        return ResponseEntity.ok(restaurantService.findAllOrders(restId, period));
    }
}