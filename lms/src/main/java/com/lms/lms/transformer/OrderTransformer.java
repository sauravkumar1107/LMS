package com.lms.lms.transformer;

import com.lms.lms.model.Order;
import com.lms.lms.model.PurchasedProduct;
import com.lms.lms.request.PlaceOrderRequest;
import com.lms.lms.request.PurchasedProductRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static Order orderRequestToOrder(PlaceOrderRequest request) {
        List<PurchasedProduct> purchasedProductList = new ArrayList<>();
        for (PurchasedProductRequest purchasedProductRequest : request.getPurchasedProducts()) {
            purchasedProductList.add(PurchasedProduct.builder()
                            .productId(purchasedProductRequest.getProductId())
                            .quantity(purchasedProductRequest.getQuantity())
                            .unitPrice(purchasedProductRequest.getUnitPrice())
                    .build());
        }

        return Order.builder()
                .id(UUID.randomUUID().toString())
                .kamId(request.getKamId())
                .purchasedProducts(purchasedProductList)
                .restBuyerId(request.getRestBuyerId())
                .restId(request.getRestId())
                .totalPrice(request.getTotalPrice())
                .orderDate(Instant.now())
                .build();
    }
}
