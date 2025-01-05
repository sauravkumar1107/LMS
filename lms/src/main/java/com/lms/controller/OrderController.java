package com.lms.controller;

import com.lms.model.Order;
import com.lms.request.Period;
import com.lms.request.PlaceOrderRequest;
import com.lms.service.OrderService;
import com.lms.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final RestaurantService restaurantService;

    @Operation(
            description = "Place an order for a restaurant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order placed successfully",
            content = @Content(schema = @Schema(implementation = Order.class))
    )
    @PostMapping()
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @Operation(
            description = "Find all orders of a restaurant in a given period"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders fetched"
    )
    @GetMapping("/restaurant/{id}/{period}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(@PathVariable("id") String id,
                                                              @PathVariable("period") Period period) {
        return ResponseEntity.ok(restaurantService.findAllOrders(id, period));
    }
}
