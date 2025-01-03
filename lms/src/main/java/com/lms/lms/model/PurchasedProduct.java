package com.lms.lms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PurchasedProduct {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
}
