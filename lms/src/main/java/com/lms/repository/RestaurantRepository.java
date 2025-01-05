package com.lms.repository;

import com.lms.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    List<Restaurant> findByKamId(String kamId);

    @Query(value = """
            SELECT r
            FROM Restaurant r
            JOIN r.orders o
            WHERE r.kamId = :kamId
              AND o.orderDate BETWEEN :startDate AND :endDate
            GROUP BY r.id
            ORDER BY SUM(o.totalPrice) DESC
            """)
    List<Restaurant> findTopPerformingRestaurants(
            @Param("kamId") String kamId,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );

    @Query(value = """
            SELECT r
            FROM Restaurant r
            JOIN r.orders o
            WHERE r.kamId = :kamId
              AND o.orderDate BETWEEN :startDate AND :endDate
            GROUP BY r.id
            ORDER BY SUM(o.totalPrice) ASC
            """)
    List<Restaurant> findWorstPerformingRestaurants(
            @Param("kamId") String kamId,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );
}
