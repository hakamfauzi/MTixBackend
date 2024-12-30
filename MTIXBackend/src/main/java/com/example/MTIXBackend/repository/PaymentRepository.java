package com.example.MTIXBackend.repository;

import com.example.MTIXBackend.model.Payment;
import com.example.MTIXBackend.model.TiketReguler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // JpaRepository provides basic CRUD operations out-of-the-box.
    // Add custom queries here if needed.
    @Query(value = "SELECT * FROM payment t WHERE t.keranjang_id = :keranjangId", nativeQuery = true)
    List<Payment> findByKeranjangId(@Param("keranjangId") Integer keranjangId);
}