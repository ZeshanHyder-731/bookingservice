package com.esi.bookingservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookingtable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private Integer id;
    private Integer userId;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}



