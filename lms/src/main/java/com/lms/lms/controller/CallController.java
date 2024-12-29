package com.lms.lms.controller;

import com.lms.lms.request.MakeCallRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("call")
public class CallController {
    @PostMapping("/make")
    public void makeCall(@RequestBody MakeCallRequest request) {

    }

    @GetMapping("/findScheduledCalls/{kamId}")
    public List<String> findAllCalls(@PathVariable("kamId") String kamId) {
        return null;
    }
}
