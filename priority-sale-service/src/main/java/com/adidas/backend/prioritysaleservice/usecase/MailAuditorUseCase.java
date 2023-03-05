package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.commons.client.kafka.RecoverWinnerProducer;
import com.adidas.backend.prioritysaleservice.commons.client.kafka.model.QueueMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableAsync
@RequiredArgsConstructor
public class MailAuditorUseCase {

  private final RecoverWinnerProducer recoverWinnerProducer;

  /**
   * The scheduler for validate the winner
   * It is delayed 5 after service init
   * It restarts again every 1 minute
   */
  @Async
  @Scheduled(fixedDelay = 60000, initialDelay = 5000)
  public void validateMail() {
    log.debug("Sending message: looking for a new winner");
    recoverWinnerProducer.send(QueueMessages.TIME_TO_SEND_MAIL);
  }

}
