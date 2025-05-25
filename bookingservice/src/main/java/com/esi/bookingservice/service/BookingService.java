package com.esi.bookingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.bookingservice.dto.BookingDto;
import com.esi.bookingservice.model.Booking;
import com.esi.bookingservice.model.BookingStatus;
import com.esi.bookingservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private OrderRepository orderRepository;

    private final KafkaTemplate<String, BookingDto> kafkaTemplate;

    @KafkaListener(topics = "alertTopic", groupId = "orderPaymentGroup" )
    public void updatePaymentinfo(BookingDto bookingDto){
        Booking order = Booking.builder()
        .id(bookingDto.getId())
        .userId(bookingDto.getUserId())
        .price(bookingDto.getPrice())
        .bookingStatus(bookingDto.getBookingStatus())
        .build();
        orderRepository.save(order);
    log.info("Order {} payment status updated", order.getId());
    }

    public   List<BookingDto> getAllOrders(){
        List<Booking> orders =  new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders.stream().map(this::mapToBookingDto).toList();
    }    
        
    private BookingDto mapToBookingDto(Booking order) {
            return BookingDto.builder()
                    .id(order.getId())
                    .userId(order.getUserId())
                    .price(order.getPrice())
                    .build();
    }

    public void addOrder(BookingDto bookingDto) {
            Booking order = Booking.builder()
            .id(bookingDto.getId())
            .userId(bookingDto.getUserId())
            .price(bookingDto.getPrice())
            .build();

            // Setting the Order status to CREATED, and the payment status to Pending
            order.setBookingStatus(BookingStatus.BOOKING_CREATED);
            // order.setPaymentStatus(BookingStatus.PAYMENT_PENDING);

            // Save the order to in its current state in the Database
            orderRepository.save(order);

            // Setting the bookingDto status to CREATED, and the payment status to Pending
            bookingDto.setBookingStatus(BookingStatus.BOOKING_CREATED);
            // bookingDto.setPaymentStatus(PaymentStatus.PAYMENT_PENDING);

            // Push the bookingDto to the bookingCreatedTopic topic
            kafkaTemplate.send("bookingCreatedTopic", bookingDto);

        log.info("An order id: {} is added to the Database", order.getId());
    }
    
        public void updateOrder(Integer id, BookingDto bookingDto) {
            Booking order = Booking.builder()
            .id(bookingDto.getId())
            .userId(bookingDto.getUserId())
            .price(bookingDto.getPrice())
            .build();
            orderRepository.save(order);
        log.info("Order {} is updated", order.getId());
        }

        public void deleteOrder(Integer id) {
            orderRepository.deleteById(id);
        log.info("Order {} is deleted", id);
        } 
}
