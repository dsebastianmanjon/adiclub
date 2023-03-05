package com.adidas.backend.prioritysaleservice.commons.client.kafka;

import com.adidas.backend.prioritysaleservice.commons.client.kafka.model.QueueMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class RecoverWinnerProducer {

  @Value("${adidas.kafka.topic.queue-to-priority-request}")
  private String topic;

  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * It sends the operation TIME_TO_SEND_MAIL | CLEAR to Kafka
   * TIME_TO_SEND_MAIL: It sends the winner
   * CLEAR: clear the queue
   * @param message The operation to Kafka
   */
  public void send(QueueMessages message) {
    kafkaTemplate.send(topic, message.name());
    log.info("Message sent: {}", message);
  }

}
