package com.adidas.backend.emailservice.commons.client.kafka;

import com.adidas.backend.emailservice.usecase.WinnerMailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMailReceiver {

  private final WinnerMailUseCase winnerMailUseCase;


  /**
   * It receives the winner to send the info
   * @param data The message
   */
  @KafkaListener(topics = "${adidas.kafka.topic.priority-to-mail}")
  public void receive(String data) {
    winnerMailUseCase.sendMail(data);
  }

}
