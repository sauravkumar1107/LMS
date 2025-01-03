package com.lms.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String kamId;
    private String restId;
    private String restBuyerId;
    private List<PurchasedProduct> purchasedProducts;
    private Integer totalPrice;
    private Instant orderDate;
}
