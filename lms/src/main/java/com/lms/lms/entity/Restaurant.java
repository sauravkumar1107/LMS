package com.lms.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Restaurant {
    private String id;
    private String kamId;
    private RestaurantMetadata metadata;
    private Status status;
    private List<String> interactions;
    private List<String> orders;
    private String lastInteractionId;
    private CallFrequency frequency;
}
