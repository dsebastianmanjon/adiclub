package com.adidas.backend.queueservice.usecase;

import com.adidas.backend.queueservice.commons.InstantTypeConverter;
import com.adidas.backend.queueservice.commons.client.kafka.WinnerProducer;
import com.adidas.backend.queueservice.commons.client.kafka.model.QueueMessages;
import com.adidas.backend.queueservice.singleton.SubscriberQueue;
import com.adidas.backend.queueservice.singleton.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecoverAdiClubMemberSendMailUseCase {

  private final WinnerProducer winnerProducer;

  /**
   * It runs the input operation
   * @param operation The operation TIME_TO_SEND_MAIL | CLEAR
   */
  public void runOperation(String operation) {
    executeOperation(operation);
  }

  private void executeOperation(String operation) {
    switch ( QueueMessages.valueOf(operation)) {
      case TIME_TO_SEND_MAIL:
        Optional<AdiClubMemberInfo> member = recoverWinnerFromQueue();
        member.ifPresent(this::responseToPriorityService);
        break;
      case CLEAR:
        clearQueue();
        break;
      default:
        log.error("Invalid operation {}", operation);
    }
  }

  private Optional<AdiClubMemberInfo> recoverWinnerFromQueue() {
    return Optional.ofNullable(SubscriberQueue.getQueue().poll());
  }

  private void responseToPriorityService(AdiClubMemberInfo memberInfo) {
    winnerProducer.send(parseToString(memberInfo));
    log.info("Sending the winner {}", memberInfo);
  }

  private String parseToString(AdiClubMemberInfo subscriberInfo) {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .toJson(subscriberInfo);
  }

  private void clearQueue() {
    SubscriberQueue.getQueue().clear();
  }

}
