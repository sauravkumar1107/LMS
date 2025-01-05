package com.lms.service;

import com.lms.model.Order;
import com.lms.model.Product;
import com.lms.model.PurchasedProduct;
import com.lms.model.Restaurant;
import com.lms.repository.OrderRepository;
import com.lms.repository.ProductRepository;
import com.lms.repository.RestaurantRepository;
import com.lms.request.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    public Order placeOrder(PlaceOrderRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getRestId())
                .orElseThrow(() -> new RuntimeException("Data not found"));

                log.info("before product fetched");


        List<PurchasedProduct> purchasedProducts = request.getPurchasedProducts().stream().map(pp -> {
            Product product = productRepository.findById(pp.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + pp.getProductId()));

            log.info("product fetched");
            return PurchasedProduct.builder()
                    .product(product)
                    .quantity(pp.getQuantity())
                    .id(UUID.randomUUID().toString())
                    .build();
        }).toList();

        log.info("after product fetched");


        Order order = Order.builder()
                .kamId(restaurant.getKamId())
                .restBuyerId(request.getRestBuyerId())
                .restaurant(restaurant)
                .totalPrice(request.getTotalPrice())
                .orderDate(Instant.now())
                .id(UUID.randomUUID().toString())
                .purchasedProducts(purchasedProducts)
                .build();

        return orderRepository.save(order);
    }
}
