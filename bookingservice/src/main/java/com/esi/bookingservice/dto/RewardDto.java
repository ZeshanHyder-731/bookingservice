package com.esi.bookingservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RewardDto {
    @Id
    private Long userId;
    @Enumerated(EnumType.STRING)
    private RewardStatus rewardStatus;
}