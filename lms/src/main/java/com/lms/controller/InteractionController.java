package com.lms.controller;

import com.lms.model.Call;
import com.lms.model.Restaurant;
import com.lms.request.MakeCallRequest;
import com.lms.service.InteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/interaction")
public class InteractionController {
    private final InteractionService interactionService;

    @Operation(
            description = "Make a call to a contact of a restaurant lead"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Call made successfully",
            content = @Content(schema = @Schema(implementation = Call.class))
    )
    @PostMapping()
    public ResponseEntity<Call> makeCall(@RequestBody MakeCallRequest request) {
        return ResponseEntity.ok(interactionService.makeCall(request));
    }

    @Operation(
            description = "Find all calls scheduled/planned for today for a KAM"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Scheduled calls fetched"
    )
    @GetMapping("/kam/{id}")
    public List<Restaurant> findAllCalls(@PathVariable("id") String kamId) {
        return interactionService.getTodaysScheduledCalls(kamId);
    }
}
