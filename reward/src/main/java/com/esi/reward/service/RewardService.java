package com.esi.reward.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.reward.dto.BookingDto;
import com.esi.reward.dto.RewardDto;
import com.esi.reward.model.RewardStatus;

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

      RewardDto rewardDto = new RewardDto();
      rewardDto.setUserId(bookingDto.getUserId().longValue());

      if (bookingDto.getPrice().doubleValue() > 1000) {
          rewardDto.setRewardStatus(RewardStatus.REWARD_GIFTED);
      } else {
          rewardDto.setRewardStatus(RewardStatus.REWARD_FAILED);
      }

      // Send reward info to alertTopic
      kafkaTemplate.send("rewardTopic", rewardDto);
      log.info("Sent alert to alertTopic: {}", rewardDto);
  }
}
