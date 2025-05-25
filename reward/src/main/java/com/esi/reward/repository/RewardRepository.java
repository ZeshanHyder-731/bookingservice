package com.esi.reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.reward.model.Reward;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
}