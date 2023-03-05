package com.adidas.backend.prioritysaleservice.commons.client.kafka;

import com.adidas.backend.prioritysaleservice.usecase.SendMailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WinnerReceiver {

  private final SendMailUseCase sendMailUseCase;


  /**
   * It receives the message sent from queue service
   * @param data The subscriber info
   */
  @KafkaListener(topics = "${adidas.kafka.topic.queue-to-priority-response}")
  public void receive(String data) {
    sendMailUseCase.sendMail(data);
  }

}
