package com.lms.controller;

import com.lms.model.Order;
import com.lms.request.Period;
import com.lms.request.PlaceOrderRequest;
import com.lms.service.OrderService;
import com.lms.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    private OrderService orderService;
    private RestaurantService restaurantService;
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderService = mock(OrderService.class);
        restaurantService = mock(RestaurantService.class);
        orderController = new OrderController(orderService, restaurantService);
    }

    @Test
    void testPlaceOrder() {
        PlaceOrderRequest request = PlaceOrderRequest.builder().build();
        Order expectedOrder = Order.builder().build();
        when(orderService.placeOrder(any(PlaceOrderRequest.class))).thenReturn(expectedOrder);

        ResponseEntity<Order> response = orderController.placeOrder(request);

        verify(orderService, times(1)).placeOrder(any(PlaceOrderRequest.class));
        assertEquals(expectedOrder, response.getBody());
    }

    @Test
    void testGetAllRestaurantOrders() {
        String restaurantId = "123";
        Period period = Period.DAY;
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());
        when(restaurantService.findAllOrders(eq(restaurantId), any(Period.class))).thenReturn(expectedOrders);

        ResponseEntity<List<Order>> response = orderController.getAllRestaurantOrders(restaurantId, period);

        verify(restaurantService, times(1)).findAllOrders(eq(restaurantId), any(Period.class));
        assertEquals(expectedOrders, response.getBody());
    }
}
