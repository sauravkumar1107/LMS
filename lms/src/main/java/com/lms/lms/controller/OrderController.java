package com.lms.lms.controller;

import com.lms.lms.request.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("order")
public class OrderController {
    @PostMapping("/place")
    public void placeOrder(@RequestBody PlaceOrderRequest request) {

    }
}
