package com.lms.lms.transformer;

import com.lms.lms.model.Call;
import com.lms.lms.request.MakeCallRequest;

import java.time.Instant;
import java.util.UUID;

public class CallTransformer {
    public static Call makeCallRequestToCallModel(MakeCallRequest request) {
        return Call.builder()
                .callId(UUID.randomUUID().toString())
                .contactId(request.getContactId())
                .orderId(request.getOrderId())
                .kamId(request.getKamId())
                .restId(request.getRestId())
                .callTime(Instant.now())
                .build();
    }
}
