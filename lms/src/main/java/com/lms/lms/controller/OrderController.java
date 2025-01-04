package com.lms.lms.controller;

import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.PlaceOrderRequest;
import com.lms.lms.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @Operation(
            description = "Place an order for a restaurant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order placed successfully",
            content = @Content(schema = @Schema(implementation = Order.class))
    )
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }
}
