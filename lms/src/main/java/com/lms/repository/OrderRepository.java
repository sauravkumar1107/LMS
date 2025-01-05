package com.lms.repository;

import com.lms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT o FROM Order o WHERE o.restaurant.id = :restaurantId AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findAllByRestaurantIdAndOrderDateAfter(
            @Param("restaurantId") String restaurantId,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );
}
