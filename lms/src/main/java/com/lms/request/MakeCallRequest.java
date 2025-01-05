package com.lms.request;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MakeCallRequest {
    private String restId;
    private String kamId;
    private String contactId;
    private String orderId;
}
