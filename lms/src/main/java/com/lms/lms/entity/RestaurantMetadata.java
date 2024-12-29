package com.lms.lms.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestaurantMetadata {
    private String name;
    private String address;
    private Integer starsRating;
}
