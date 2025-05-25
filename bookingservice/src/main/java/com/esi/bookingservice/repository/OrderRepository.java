package com.esi.bookingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.bookingservice.model.Booking;

public interface OrderRepository extends JpaRepository<Booking, Integer> {

    Optional<Booking> findTopByUserIdOrderByIdDesc(Long userId);
}

