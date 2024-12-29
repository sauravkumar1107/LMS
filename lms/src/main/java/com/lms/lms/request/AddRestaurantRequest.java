package com.lms.lms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddRestaurantRequest {
    private String id;
    private String kamId;
    private String name;
    private String address;
    private Integer starsRating;
    private String frequency;
}
