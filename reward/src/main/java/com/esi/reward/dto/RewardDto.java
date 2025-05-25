package com.esi.reward.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RewardDto {
    @Id
    private Long userId;
    private String Message;
    private LocalDateTime timestamp;
}