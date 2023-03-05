package com.adidas.backend.queueservice.commons.client.kafka;

import com.adidas.backend.queueservice.usecase.RecoverAdiClubMemberSendMailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoverWinnerReceiver {

  private final RecoverAdiClubMemberSendMailUseCase recoverAdiClubMemberSendMailUseCase;

  /**
   * It receives the winner from queue to priority
   * @param data The winner subscriber
   */
  @KafkaListener(topics = "${adidas.kafka.topic.queue-to-priority-request}")
  public void receive(String data) {
    recoverAdiClubMemberSendMailUseCase.runOperation(data);
  }

}
