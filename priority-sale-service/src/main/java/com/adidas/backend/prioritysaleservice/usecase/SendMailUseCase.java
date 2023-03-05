package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.commons.client.kafka.SendMailProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailUseCase {

  private final SendMailProducer sendMailProducer;

  /**
   * It sends the winner from this service to the priority service
   * @param winnerMember winnerMember
   */
  public void sendMail(String winnerMember) {
    sendMailProducer.send(winnerMember);
    log.info("Sent winner {}", winnerMember);
  }

}
