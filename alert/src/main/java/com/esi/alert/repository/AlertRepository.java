package com.esi.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.alert.model.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
}
