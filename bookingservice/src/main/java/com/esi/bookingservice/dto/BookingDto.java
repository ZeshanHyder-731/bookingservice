package com.esi.bookingservice.dto;

import java.math.BigDecimal;

import com.esi.bookingservice.model.BookingStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;
    private Integer userId;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}