package com.lms.lms.repository;

import com.lms.lms.model.Call;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, String> {
    List<Call> findByKamId(String kamId);
}