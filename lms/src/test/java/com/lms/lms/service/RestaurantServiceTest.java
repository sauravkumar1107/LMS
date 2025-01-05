package com.lms.lms.service;

import com.lms.lms.model.Contact;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.repository.ContactRepository;
import com.lms.lms.repository.OrderRepository;
import com.lms.lms.repository.RestaurantRepository;
import com.lms.lms.request.AddRestaurantRequest;
import com.lms.lms.request.ContactRequest;
import com.lms.lms.request.Period;
import com.lms.lms.request.UpdateRestaurantDataRequest;
import com.lms.lms.transformer.ContactTransformer;
import com.lms.lms.transformer.RestaurantTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    private RestaurantRepository restaurantRepository;
    private ContactRepository contactRepository;
    private OrderRepository orderRepository;
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantRepository = mock(RestaurantRepository.class);
        contactRepository = mock(ContactRepository.class);
        orderRepository = mock(OrderRepository.class);
        restaurantService = new RestaurantService(restaurantRepository, contactRepository, orderRepository);
    }

    @Test
    void testAddRestaurant_Success() {
        // Arrange
        AddRestaurantRequest request = AddRestaurantRequest.builder()
                .kamId("testKamId")
                .name("testName")
                .frequency("DAILY")
                .build();

        Restaurant restaurant = Restaurant.builder()
                .id("testId")
                .kamId("testKamId")
                .name("testName")
                .build();
        when(restaurantRepository.save(any(Restaurant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Restaurant result = restaurantService.addRestaurant(request);

        // Assert
        assertEquals(restaurant.getName(), result.getName());
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    void testGetAllRestaurants_Success() {
        // Arrange
        String kamId = "kam123";
        List<Restaurant> restaurants = Arrays.asList(new Restaurant(), new Restaurant());
        when(restaurantRepository.findByKamId(kamId)).thenReturn(restaurants);

        // Act
        List<Restaurant> result = restaurantService.getAllRestaurants(kamId);

        // Assert
        assertEquals(2, result.size());
        verify(restaurantRepository, times(1)).findByKamId(kamId);
    }

    @Test
    void testGetRestaurantContacts_Success() {
        // Arrange
        String restId = "rest123";
        List<Contact> contacts = Arrays.asList(new Contact(), new Contact());
        when(contactRepository.findByRestId(restId)).thenReturn(contacts);

        // Act
        List<Contact> result = restaurantService.getRestaurantContacts(restId);

        // Assert
        assertEquals(2, result.size());
        verify(contactRepository, times(1)).findByRestId(restId);
    }

    @Test
    void testUpdateRestaurant_Success() {
        // Arrange
        String restaurantId = "rest123";
        UpdateRestaurantDataRequest request = UpdateRestaurantDataRequest.builder().build();

        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Restaurant result = restaurantService.updateRestaurant(restaurantId, request);

        // Assert
        assertEquals(restaurant, result);
        verify(contactRepository, times(0)).save(any(Contact.class));
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void testUpdateRestaurant_NotFound() {
        // Arrange
        String restaurantId = "rest123";
        UpdateRestaurantDataRequest request = UpdateRestaurantDataRequest.builder().build();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> restaurantService.updateRestaurant(restaurantId, request));
        assertEquals("Data not found", exception.getMessage());
        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void testFindAllOrders_Success() {
        // Arrange
        String restId = "rest123";
        Period period = Period.WEEK;
        Instant now = Instant.now();
        List<Order> orders = Arrays.asList(new Order(), new Order());

        when(orderRepository.findAllByRestaurantIdAndOrderDateAfter(eq(restId), any(Instant.class), any(Instant.class)))
                .thenReturn(orders);

        // Act
        List<Order> result = restaurantService.findAllOrders(restId, period);

        // Assert
        assertEquals(2, result.size());
        verify(orderRepository, times(1))
                .findAllByRestaurantIdAndOrderDateAfter(eq(restId), any(Instant.class), any(Instant.class));
    }

    @Test
    void testGetPerformers_TopPerformers() {
        // Arrange
        String kamId = "kam123";
        Period period = Period.MONTH;
        int inc = 1;
        List<Restaurant> performers = Arrays.asList(new Restaurant(), new Restaurant());

        when(restaurantRepository.findTopPerformingRestaurants(eq(kamId), any(Instant.class), any(Instant.class)))
                .thenReturn(performers);

        // Act
        List<Restaurant> result = restaurantService.getPerformers(kamId, period, inc);

        // Assert
        assertEquals(2, result.size());
        verify(restaurantRepository, times(1))
                .findTopPerformingRestaurants(eq(kamId), any(Instant.class), any(Instant.class));
    }

    @Test
    void testGetPerformers_WorstPerformers() {
        // Arrange
        String kamId = "kam123";
        Period period = Period.MONTH;
        int inc = 0;
        List<Restaurant> performers = Arrays.asList(new Restaurant(), new Restaurant());

        when(restaurantRepository.findWorstPerformingRestaurants(eq(kamId), any(Instant.class), any(Instant.class)))
                .thenReturn(performers);

        // Act
        List<Restaurant> result = restaurantService.getPerformers(kamId, period, inc);

        // Assert
        assertEquals(2, result.size());
        verify(restaurantRepository, times(1))
                .findWorstPerformingRestaurants(eq(kamId), any(Instant.class), any(Instant.class));
    }
}
