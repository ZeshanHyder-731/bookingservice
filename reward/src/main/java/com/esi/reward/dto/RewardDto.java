package com.esi.reward.dto;

import com.esi.reward.model.RewardStatus;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RewardDto {
    @Id
    private Long userId;
    private RewardStatus rewardStatus;
}