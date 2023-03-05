package com.adidas.backend.prioritysaleservice.commons.client.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class SendMailProducer {

  @Value("${adidas.kafka.topic.priority-to-mail}")
  private String topic;

  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * It sends the winner from this service to the priority service, Kafka via
   * @param message The subscriber info
   */
  public void send(String message) {
    kafkaTemplate.send(topic, message);
    log.info("Winner sent: {}", message);
  }

}
