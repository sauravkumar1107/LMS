package com.lms.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calls")
public class Call {
    @Id
    private String callId;
    private String restId;
    private String kamId;
    private String contactId;
    private String orderId;
    private Instant callTime;
}
