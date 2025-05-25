package com.esi.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.bookingservice.dto.BookingDto;
import com.esi.bookingservice.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private BookingService orderService;

    @GetMapping("/orders")
    public List<BookingDto> getOrders() {
    return orderService.getAllOrders();
    }
    
    @PostMapping(value = "/orders")
    public ResponseEntity<String>  createOrder(@RequestBody BookingDto orderDto) {
    orderService.addOrder(orderDto);
    return ResponseEntity.ok("Order created");

    }
}
