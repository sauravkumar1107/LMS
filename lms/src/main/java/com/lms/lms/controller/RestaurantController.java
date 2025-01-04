package com.lms.lms.controller;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.AddRestaurantRequest;
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
@RequestMapping("api/v1/restaurant")
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
    @PostMapping()
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody AddRestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.addRestaurant(request));
    }

    @Operation(
            description = "Get all restaurant leads of a KAM"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurants fetched"
    )
    @GetMapping("/kam/{id}")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@PathVariable("id") String kamId) {
        return ResponseEntity.ok(restaurantService.getAllRestaurants(kamId));
    }

    @Operation(
            description = "Get all contacts of a restaurant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Contacts fetched"
    )
    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<Contact>> getContacts(@PathVariable("id") String id) {
        return ResponseEntity.ok(restaurantService.getRestaurantContacts(id));
    }

    @Operation(
            description = "Update details of a restaurant in the system, details like KAM, name, address, status, etc"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Details updated"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurantData(@PathVariable("id") String id, @RequestBody UpdateRestaurantDataRequest request) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(request));
    }

    @Operation(
            description = "Find the top or low performers for a KAM"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Details fetched"
    )
    @GetMapping("/performance/kam/{id}/{count}/{order}")
    public ResponseEntity<List<Restaurant>> getPerformers(@PathVariable("id") String kamId,
                                      @PathVariable("count") int count,
                                      @PathVariable("order") int inc) {
        return ResponseEntity.ok(restaurantService.getPerformers(kamId, count, inc));
    }
}