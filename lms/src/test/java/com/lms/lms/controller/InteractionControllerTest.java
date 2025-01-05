package com.lms.lms.controller;

import com.lms.lms.model.Call;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.MakeCallRequest;
import com.lms.lms.service.InteractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InteractionControllerTest {

    private InteractionService interactionService;
    private InteractionController interactionController;

    @BeforeEach
    void setUp() {
        interactionService = mock(InteractionService.class);
        interactionController = new InteractionController(interactionService);
    }

    @Test
    void testMakeCall() {
        MakeCallRequest request = MakeCallRequest.builder().build();
        Call expectedCall = Call.builder().build();
        when(interactionService.makeCall(any(MakeCallRequest.class))).thenReturn(expectedCall);

        ResponseEntity<Call> response = interactionController.makeCall(request);

        verify(interactionService, times(1)).makeCall(any(MakeCallRequest.class));
        assertEquals(expectedCall, response.getBody());
    }

    @Test
    void testFindAllCalls() {
        String kamId = "123";
        List<Restaurant> expectedRestaurants = Arrays.asList(new Restaurant(), new Restaurant());
        when(interactionService.getTodaysScheduledCalls(kamId)).thenReturn(expectedRestaurants);

        List<Restaurant> result = interactionController.findAllCalls(kamId);

        verify(interactionService, times(1)).getTodaysScheduledCalls(kamId);
        assertEquals(expectedRestaurants, result);
    }
}
