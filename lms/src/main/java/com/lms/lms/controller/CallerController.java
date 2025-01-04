package com.lms.lms.controller;

import com.lms.lms.model.Call;
import com.lms.lms.model.Order;
import com.lms.lms.model.Restaurant;
import com.lms.lms.request.MakeCallRequest;
import com.lms.lms.service.CallerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("call")
public class CallerController {
    private final CallerService callerService;

    @Operation(
            description = "Make a call to a contact of a restaurant lead"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Call made successfully",
            content = @Content(schema = @Schema(implementation = Call.class))
    )
    @PostMapping("/make")
    public ResponseEntity<Call> makeCall(@RequestBody MakeCallRequest request) {
        return ResponseEntity.ok(callerService.makeCall(request));
    }

    @Operation(
            description = "Find all calls scheduled/planned for today for a KAM"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Scheduled calls fetched"
    )
    @GetMapping("/findScheduledCalls/{kamId}")
    public List<Restaurant> findAllCalls(@PathVariable("kamId") String kamId) {
        return callerService.getTodaysScheduledCalls(kamId);
    }
}
