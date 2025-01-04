package com.lms.lms.service;

import com.lms.lms.model.Order;
import com.lms.lms.model.Product;
import com.lms.lms.model.PurchasedProduct;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.ProductRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.PlaceOrderRequest;
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

        List<PurchasedProduct> purchasedProducts = request.getPurchasedProducts().stream().map(pp -> {
            Product product = productRepository.findById(pp.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + pp.getProductId()));

            return PurchasedProduct.builder()
                    .product(product)
                    .quantity(pp.getQuantity())
                    .id(UUID.randomUUID().toString())
                    .build();
        }).toList();

        Order order = Order.builder()
                .kamId(request.getKamId())
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
