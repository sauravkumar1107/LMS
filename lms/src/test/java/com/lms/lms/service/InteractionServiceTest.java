package com.lms.lms.service;

import com.lms.lms.model.*;
import com.lms.lms.repository.CallRepository;
import com.lms.lms.repository.ContactRepository;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.MakeCallRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InteractionServiceTest {

    private CallRepository callRepository;
    private RestaurantRepository restaurantRepository;
    private ContactRepository contactRepository;
    private OrderRepository orderRepository;
    private InteractionService interactionService;

    @BeforeEach
    void setUp() {
        callRepository = mock(CallRepository.class);
        restaurantRepository = mock(RestaurantRepository.class);
        contactRepository = mock(ContactRepository.class);
        orderRepository = mock(OrderRepository.class);
        interactionService = new InteractionService(callRepository, restaurantRepository, contactRepository, orderRepository);
    }

    @Test
    void testMakeCall_WithOrder() {
        MakeCallRequest request = MakeCallRequest.builder().build();
        request.setRestId("rest123");
        request.setContactId("contact456");
        request.setOrderId("order789");
        request.setKamId("kam001");

        Restaurant restaurant = new Restaurant();
        restaurant.setLastCallTime(Instant.now());
        restaurant.setStatus(Status.ACTIVE);

        Contact contact = new Contact();
        Order order = new Order();

        when(restaurantRepository.findById(request.getRestId())).thenReturn(Optional.of(restaurant));
        when(contactRepository.findById(request.getContactId())).thenReturn(Optional.of(contact));
        when(orderRepository.findById(request.getOrderId())).thenReturn(Optional.of(order));
        when(callRepository.save(any(Call.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        Call result = interactionService.makeCall(request);

        assertEquals(restaurant, result.getRestaurant());
        assertEquals(contact, result.getContact());
        assertEquals(order, result.getOrder());
        assertEquals(Status.ORDER_PLACED, restaurant.getStatus());
        verify(restaurantRepository, times(1)).save(restaurant);
        verify(callRepository, times(1)).save(any(Call.class));
    }

    @Test
    void testMakeCall_WithoutOrder() {
        MakeCallRequest request = MakeCallRequest.builder().build();
        request.setRestId("rest123");
        request.setContactId("contact456");
        request.setKamId("kam001");

        Restaurant restaurant = new Restaurant();
        restaurant.setLastCallTime(Instant.now());
        restaurant.setStatus(Status.ACTIVE);

        Contact contact = new Contact();

        when(restaurantRepository.findById(request.getRestId())).thenReturn(Optional.of(restaurant));
        when(contactRepository.findById(request.getContactId())).thenReturn(Optional.of(contact));
        when(callRepository.save(any(Call.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        Call result = interactionService.makeCall(request);

        assertEquals(restaurant, result.getRestaurant());
        assertEquals(contact, result.getContact());
        assertEquals(Status.CONTACTED, restaurant.getStatus());
        verify(restaurantRepository, times(1)).save(restaurant);
        verify(callRepository, times(1)).save(any(Call.class));
    }

    @Test
    void testGetTodaysScheduledCalls() {
        // Arrange
        String kamId = "kam001";
        Restaurant restaurant1 = Restaurant.builder()
                .id("test")
                .lastCallTime(Instant.now().minus(1, ChronoUnit.DAYS))
                .frequency(CallFrequency.DAILY)
                .build();
        List<Restaurant> restaurants = Arrays.asList(restaurant1);

        when(restaurantRepository.findByKamId(kamId)).thenReturn(restaurants);

        List<Restaurant> result = interactionService.getTodaysScheduledCalls(kamId);

        assertEquals(1, result.size());
        assertEquals(restaurant1, result.get(0));
        verify(restaurantRepository, times(1)).findByKamId(kamId);
    }
}
