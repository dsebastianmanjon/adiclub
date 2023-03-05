package com.adidas.backend.queueservice.commons.client.kafka;

import com.adidas.backend.queueservice.usecase.AddElementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueReceiver {

  private final AddElementUseCase addElementUseCase;

  /**
   * It receives the message from priority to queue
   * @param data The subscriber to add in the queue
   */
  @KafkaListener(topics = "${adidas.kafka.topic.priority-to-queue}")
  public void receive(String data) {
    addElementUseCase.addQueue(data);
  }

}
