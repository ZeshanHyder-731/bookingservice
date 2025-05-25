package com.esi.alert.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.alert.dto.BookingDto;
import com.esi.alert.model.Alert;
import com.esi.alert.repository.AlertRepository;
import com.esi.alert.dto.AlertDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    private final KafkaTemplate<String, AlertDto> kafkaTemplate;

    @KafkaListener(topics = "bookingCreatedTopic", groupId = "alertGroup")
    public void processBookingCreated(BookingDto bookingDto) {
        log.info("Received booking from bookingCreatedTopic: {}", bookingDto);

    AlertDto alertDto = new AlertDto();
    alertDto.setUserId(bookingDto.getUserId().longValue());
    alertDto.setMessage("New booking received for user ID: " + bookingDto.getUserId());
    alertDto.setTimestamp(LocalDateTime.now());

    // Convert DTO to entity before saving
    Alert alertEntity = new Alert();
    alertEntity.setUserId(alertDto.getUserId());
    alertEntity.setMessage(alertDto.getMessage());
    alertEntity.setTimestamp(alertDto.getTimestamp());

    alertRepository.save(alertEntity);

    kafkaTemplate.send("alertTopic", alertDto);
    log.info("Sent alert to alertTopic: {}", alertDto);
    }
}
