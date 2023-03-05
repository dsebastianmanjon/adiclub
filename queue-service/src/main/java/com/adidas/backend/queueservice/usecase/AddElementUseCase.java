package com.adidas.backend.queueservice.usecase;

import com.adidas.backend.queueservice.commons.InstantTypeConverter;
import com.adidas.backend.queueservice.singleton.SubscriberQueue;
import com.adidas.backend.queueservice.singleton.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class AddElementUseCase {

  /**
   * It adds a new subscriber to the queue
   * @param newAdiClubUser The new subscriber to add in the queue
   */
  public void addQueue(String newAdiClubUser) {
    AdiClubMemberInfo parsedNewAdiClubUser = parseToObject(newAdiClubUser);
    SubscriberQueue.getQueue().add(parsedNewAdiClubUser);
    log.info("Received new message: {}", parsedNewAdiClubUser);

  }

  private AdiClubMemberInfo parseToObject(String newAdiClubUser) {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .fromJson(newAdiClubUser, AdiClubMemberInfo.class);
  }

}
