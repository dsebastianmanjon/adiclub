package com.adidas.backend.publicservice.usecase;

import com.adidas.backend.publicservice.commons.client.priority.PrioritySaleClient;
import com.adidas.backend.publicservice.commons.client.priority.model.SubscriptionClientRequest;
import com.adidas.backend.publicservice.exceptions.ResponseErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionUseCase {

  private final PrioritySaleClient prioritySaleClient;

  /**
   * It creates a new subscriber
   * @param newMailSubscription The new mail subscription
   */
  public void createSubscription(String newMailSubscription) {
    log.info("Adding new user: {}", newMailSubscription);

    try{
      prioritySaleClient.addNewUser(
          SubscriptionClientRequest.builder()
              .userMail(newMailSubscription)
              .build());
    } catch (Exception e) {
      throw new ResponseErrorException("Error calling to Priority service: "
          + e.getLocalizedMessage());
    }

  }

}
