package com.adidas.backend.queueservice.commons.client.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class WinnerProducer {

  @Value("${adidas.kafka.topic.queue-to-priority-response}")
  private String topic;

  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * It sends the subscriber from queue to priority
   * @param message The subscriber
   */
  public void send(String message) {
    kafkaTemplate.send(topic, message);
    log.info("Sending winner: {}", message);
  }

}
