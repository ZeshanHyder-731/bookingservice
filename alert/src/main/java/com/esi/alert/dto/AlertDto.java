package com.esi.alert.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AlertDto {
    @Id
    private Long userId;
    private String message;
    private LocalDateTime timestamp;
}
