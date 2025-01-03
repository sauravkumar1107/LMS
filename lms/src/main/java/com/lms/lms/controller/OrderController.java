package com.lms.lms.controller;

import com.lms.lms.request.PlaceOrderRequest;
import com.lms.lms.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public boolean placeOrder(@RequestBody PlaceOrderRequest request) {
        return orderService.placeOrder(request);
    }
}
