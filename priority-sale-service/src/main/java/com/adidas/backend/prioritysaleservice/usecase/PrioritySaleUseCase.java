package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.commons.InstantTypeConverter;
import com.adidas.backend.prioritysaleservice.commons.client.kafka.QueueProducer;
import com.adidas.backend.prioritysaleservice.facade.priority.PrioritySaleFacadeFactory;
import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrioritySaleUseCase {

  private final PrioritySaleFacadeFactory facadeFactory;
  private final QueueProducer queueProducer;

  /**
   * It receives new subscriptions to send to the queue producer
   * @param newMailSubscription the mail subscription
   */
  public void receivedNewSubscription(String newMailSubscription) {
    AdiClubMemberInfo subscriberInfo = facadeFactory
        .getPrioritySaleFacade(newMailSubscription)
        .newSubscriptionAction(newMailSubscription);
    log.info("Recovered info for subscriber {}", subscriberInfo);

    queueProducer.send(parseToString(subscriberInfo));
  }

  private String parseToString(AdiClubMemberInfo subscriberInfo) {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .toJson(subscriberInfo);
  }


}
