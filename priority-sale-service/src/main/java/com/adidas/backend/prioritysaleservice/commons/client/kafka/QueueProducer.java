package com.adidas.backend.prioritysaleservice.commons.client.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class QueueProducer {

  @Value("${adidas.kafka.topic.priority-to-queue}")
  private String topic;

  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * It sends messages to queue, Kafka via
   * @param message The adiClub message
   */
  public void send(String message) {
    kafkaTemplate.send(topic, message);
    log.info("Message sent: {}", message);
  }

}
