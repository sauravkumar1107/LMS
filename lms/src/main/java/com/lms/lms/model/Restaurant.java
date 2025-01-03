package com.lms.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
    private String recentOrderId;
    private CallFrequency frequency;
    private String lastCallId;
    private Instant lastCallTime;

    public Instant nextScheduledCall() {
        return switch (this.frequency) {
            case DAILY -> lastCallTime.plus(1, ChronoUnit.DAYS);
            case WEEKLY -> lastCallTime.plus(7, ChronoUnit.DAYS);
            case MONTHLY -> lastCallTime.plus(30, ChronoUnit.DAYS);
            case ANNUAL -> lastCallTime.plus(365, ChronoUnit.DAYS);
        };
    }
}
