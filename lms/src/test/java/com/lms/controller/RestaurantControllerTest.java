package com.lms.controller;

import com.lms.model.Contact;
import com.lms.model.Restaurant;
import com.lms.request.AddRestaurantRequest;
import com.lms.request.Period;
import com.lms.request.UpdateRestaurantDataRequest;
import com.lms.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    private RestaurantService restaurantService;
    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        restaurantService = mock(RestaurantService.class);
        restaurantController = new RestaurantController(restaurantService);
    }

    @Test
    void testAddRestaurant() {
        AddRestaurantRequest request = AddRestaurantRequest.builder().build();
        Restaurant expectedRestaurant = Restaurant.builder().build();
        when(restaurantService.addRestaurant(any(AddRestaurantRequest.class))).thenReturn(expectedRestaurant);

        ResponseEntity<Restaurant> response = restaurantController.addRestaurant(request);

        verify(restaurantService, times(1)).addRestaurant(any(AddRestaurantRequest.class));
        assertEquals(expectedRestaurant, response.getBody());
    }

    @Test
    void testGetAllRestaurants() {
        String kamId = "123";
        List<Restaurant> expectedRestaurants = Arrays.asList(new Restaurant(), new Restaurant());
        when(restaurantService.getAllRestaurants(kamId)).thenReturn(expectedRestaurants);

        ResponseEntity<List<Restaurant>> response = restaurantController.getAllRestaurants(kamId);

        verify(restaurantService, times(1)).getAllRestaurants(kamId);
        assertEquals(expectedRestaurants, response.getBody());
    }

    @Test
    void testGetContacts() {
        String restaurantId = "456";
        List<Contact> expectedContacts = Arrays.asList(new Contact(), new Contact());
        when(restaurantService.getRestaurantContacts(restaurantId)).thenReturn(expectedContacts);

        ResponseEntity<List<Contact>> response = restaurantController.getContacts(restaurantId);

        verify(restaurantService, times(1)).getRestaurantContacts(restaurantId);
        assertEquals(expectedContacts, response.getBody());
    }

    @Test
    void testUpdateRestaurantData() {
        String restaurantId = "789";
        UpdateRestaurantDataRequest request = UpdateRestaurantDataRequest.builder().build();
        Restaurant expectedRestaurant = new Restaurant();
        when(restaurantService.updateRestaurant(eq(restaurantId), any(UpdateRestaurantDataRequest.class)))
                .thenReturn(expectedRestaurant);

        ResponseEntity<Restaurant> response = restaurantController.updateRestaurantData(restaurantId, request);

        verify(restaurantService, times(1)).updateRestaurant(eq(restaurantId), any(UpdateRestaurantDataRequest.class));
        assertEquals(expectedRestaurant, response.getBody());
    }

    @Test
    void testGetPerformers() {
        String kamId = "123";
        Period period = Period.DAY;
        int inc = 1;
        List<Restaurant> expectedPerformers = Arrays.asList(new Restaurant(), new Restaurant());
        when(restaurantService.getPerformers(kamId, period, inc)).thenReturn(expectedPerformers);

        ResponseEntity<List<Restaurant>> response = restaurantController.getPerformers(kamId, period, inc);

        verify(restaurantService, times(1)).getPerformers(kamId, period, inc);
        assertEquals(expectedPerformers, response.getBody());
    }
}
