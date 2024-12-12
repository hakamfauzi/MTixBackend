package com.example.MTIXBackend.repository;

import com.example.MTIXBackend.model.TiketReguler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiketRegulerRepository extends JpaRepository<TiketReguler, Integer> {
    // JpaRepository provides basic CRUD operations out-of-the-box.
    // Add custom queries here if needed.
}