package com.lms.lms.controller;

import com.lms.lms.model.Restaurant;
import com.lms.lms.request.MakeCallRequest;
import com.lms.lms.service.CallerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("call")
public class CallerController {
    private final CallerService callerService;

    @PostMapping("/make")
    public boolean makeCall(@RequestBody MakeCallRequest request) {
        return callerService.makeCall(request);
    }

    @GetMapping("/findScheduledCalls/{kamId}")
    public List<Restaurant> findAllCalls(@PathVariable("kamId") String kamId) {
        return callerService.getTodaysScheduledCalls(kamId);
    }
}
