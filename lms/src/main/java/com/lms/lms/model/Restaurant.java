package com.lms.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    private String id;
    @Column(nullable = false)
    private String kamId;
    @Column(nullable = false)
    private String name;
    private String address;
    private Integer starsRating;
    private Status status;
    private List<String> interactions;
    private List<String> orders;
    private String lastInteractionId;
    private CallFrequency frequency;
}
