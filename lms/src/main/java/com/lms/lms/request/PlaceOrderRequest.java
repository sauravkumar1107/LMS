package com.lms.lms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PlaceOrderRequest {
    private String restId;
    private String restBuyerId;
    private List<PurchasedProductRequest> purchasedProducts;
    private Integer totalPrice;
}
