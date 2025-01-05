package com.lms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddRestaurantRequest {
    private String kamId;
    private String name;
    private String address;
    private Integer starsRating;
    private String frequency;
    private List<ContactRequest> contacts;
}
