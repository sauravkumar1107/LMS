package com.lms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PurchasedProductRequest {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
}
