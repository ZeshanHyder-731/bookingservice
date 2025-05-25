package com.esi.reward.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.reward.dto.BookingDto;
import com.esi.reward.dto.RewardDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RewardService {

    @Autowired
    private final KafkaTemplate<String, RewardDto> kafkaTemplate;

  @KafkaListener(topics = "bookingCreatedTopic", groupId = "rewardGroup")
public void processBookingCreated(BookingDto bookingDto) {
    log.info("Received booking from bookingCreatedTopic: {}", bookingDto);

    // Create RewardDto
    RewardDto rewardDto = new RewardDto();
    rewardDto.setUserId(bookingDto.getUserId().longValue());
    rewardDto.setMessage("GEO MAHARASHTRA" + bookingDto.getUserId());
    rewardDto.setTimestamp(LocalDateTime.now());

    // Send reward info to alertTopic
    kafkaTemplate.send("rewardTopic", rewardDto);
    log.info("Sent alert to alertTopic: {}", rewardDto);
}
}
