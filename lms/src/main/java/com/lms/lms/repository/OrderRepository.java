package com.lms.lms.repository;

import com.lms.lms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByRestId(String restId);
}
