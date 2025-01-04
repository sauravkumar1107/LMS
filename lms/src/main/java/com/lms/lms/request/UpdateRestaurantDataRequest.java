package com.lms.lms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UpdateRestaurantDataRequest {
    private String kamId;
    private String name;
    private String address;
    private Integer starsRating;
    private String frequency;
    private String status;
    private List<ContactRequest> contacts;
}
