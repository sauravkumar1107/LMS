package com.lms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private CallFrequency frequency;
    private Instant lastCallTime;
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Instant nextScheduledCall() {
        return switch (this.frequency) {
            case DAILY -> lastCallTime.plus(1, ChronoUnit.DAYS);
            case WEEKLY -> lastCallTime.plus(7, ChronoUnit.DAYS);
            case MONTHLY -> lastCallTime.plus(30, ChronoUnit.DAYS);
            case ANNUAL -> lastCallTime.plus(365, ChronoUnit.DAYS);
        };
    }
}
