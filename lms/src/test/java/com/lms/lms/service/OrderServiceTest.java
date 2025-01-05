package com.lms.lms.service;

import com.lms.lms.model.*;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.ProductRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.PlaceOrderRequest;
import com.lms.lms.request.PurchasedProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private RestaurantRepository restaurantRepository;
    private ProductRepository productRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        restaurantRepository = mock(RestaurantRepository.class);
        productRepository = mock(ProductRepository.class);
        orderService = new OrderService(orderRepository, restaurantRepository, productRepository);
    }

    @Test
    void testPlaceOrder_Success() {
        // Arrange
        PlaceOrderRequest request = PlaceOrderRequest.builder().build();
        request.setRestId("rest123");
        request.setRestBuyerId("buyer001");
        request.setTotalPrice(100);

        PurchasedProductRequest pp1 = new PurchasedProductRequest("prod1", 2, 100);
        PurchasedProductRequest pp2 = new PurchasedProductRequest("prod2", 3, 100);
        request.setPurchasedProducts(Arrays.asList(pp1, pp2));

        Restaurant restaurant = new Restaurant();
        restaurant.setKamId("kam001");

        Product product1 = new Product();
        product1.setId("prod1");
        product1.setName("Product1");

        Product product2 = new Product();
        product2.setId("prod2");
        product2.setName("Product2");

        when(restaurantRepository.findById(request.getRestId())).thenReturn(Optional.of(restaurant));
        when(productRepository.findById("prod1")).thenReturn(Optional.of(product1));
        when(productRepository.findById("prod2")).thenReturn(Optional.of(product2));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order result = orderService.placeOrder(request);

        // Assert
        assertEquals("kam001", result.getKamId());
        assertEquals("buyer001", result.getRestBuyerId());
        assertEquals(100, result.getTotalPrice());
        assertEquals(2, result.getPurchasedProducts().size());
        assertEquals("Product1", result.getPurchasedProducts().get(0).getProduct().getName());
        assertEquals("Product2", result.getPurchasedProducts().get(1).getProduct().getName());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_RestaurantNotFound() {
        // Arrange
        PlaceOrderRequest request = PlaceOrderRequest.builder().build();
        request.setRestId("rest123");

        when(restaurantRepository.findById(request.getRestId())).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(request));
        assertEquals("Data not found", exception.getMessage());
        verify(restaurantRepository, times(1)).findById(request.getRestId());
        verifyNoInteractions(productRepository, orderRepository);
    }

    @Test
    void testPlaceOrder_ProductNotFound() {
        // Arrange
        PlaceOrderRequest request = PlaceOrderRequest.builder().build();
        request.setRestId("rest123");

        PurchasedProductRequest pp = new PurchasedProductRequest("prod1", 2, 100);
        request.setPurchasedProducts(List.of(pp));

        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(request.getRestId())).thenReturn(Optional.of(restaurant));
        when(productRepository.findById("prod1")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(request));
        assertEquals("Product not found with ID: prod1", exception.getMessage());
        verify(restaurantRepository, times(1)).findById(request.getRestId());
        verify(productRepository, times(1)).findById("prod1");
        verifyNoInteractions(orderRepository);
    }

    @Test
    void testPlaceOrder_VerifySavedOrder() {
        // Arrange
        PlaceOrderRequest request = PlaceOrderRequest.builder().build();
        request.setRestId("rest123");
        request.setRestBuyerId("buyer001");
        request.setTotalPrice(200);

        PurchasedProductRequest pp = new PurchasedProductRequest("prod1", 5, 100);
        request.setPurchasedProducts(List.of(pp));

        Restaurant restaurant = new Restaurant();
        restaurant.setKamId("kam001");

        Product product = new Product();
        product.setId("prod1");
        product.setName("Product1");

        when(restaurantRepository.findById(request.getRestId())).thenReturn(Optional.of(restaurant));
        when(productRepository.findById("prod1")).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);

        // Act
        orderService.placeOrder(request);

        // Assert
        verify(orderRepository).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertEquals("kam001", capturedOrder.getKamId());
        assertEquals("buyer001", capturedOrder.getRestBuyerId());
        assertEquals(200, capturedOrder.getTotalPrice());
        assertEquals(1, capturedOrder.getPurchasedProducts().size());
        assertEquals("Product1", capturedOrder.getPurchasedProducts().get(0).getProduct().getName());
    }
}
