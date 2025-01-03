package com.lms.lms.service;

import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.Duration;
import com.lms.lms.request.PlaceOrderRequest;
import com.lms.lms.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    public boolean placeOrder(PlaceOrderRequest request) {
        Order order = OrderTransformer.orderRequestToOrder(request);
        orderRepository.save(order);

        Restaurant restaurant = restaurantRepository.findById(request.getRestId())
                .orElseThrow(() -> new RuntimeException("Data not found"));
        restaurant.setRecentOrderId(order.getId());
        restaurantRepository.save(restaurant);
        return true;
    }

    public List<Order> findAllOrders(String restId, Duration duration) {
        List<Order> orders = orderRepository.findByRestId(restId);
        int days = switch (duration) {
            case DAY -> 1;
            case WEEK -> 7;
            case MONTH -> 30;
            case YEAR -> 3665;
            default -> throw new UnsupportedOperationException("Duration is not supported");
        };

        orders.removeIf(order -> {
            if (java.time.Duration.between(order.getOrderDate(), Instant.now()).toDays() <= days) {
                return false;
            }
            return true;
        });

        return orders;
    }
}
