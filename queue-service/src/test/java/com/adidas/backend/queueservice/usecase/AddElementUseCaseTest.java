package com.adidas.backend.queueservice.usecase;

import com.adidas.backend.queueservice.commons.InstantTypeConverter;
import com.adidas.backend.queueservice.singleton.SubscriberQueue;
import com.adidas.backend.queueservice.singleton.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AddElementUseCaseTest {

  private final static String mail = "venomdoesntreallylovetoantivenom@marv3l.com";

  private final static AddElementUseCase testObj = new AddElementUseCase();
  private final static AdiClubMemberInfo adiClubMemberInfo = AdiClubMemberInfo.builder()
      .email(mail)
      .points(1000)
      .registrationDate(Instant.ofEpochMilli(73264271044L))
      .build();

  @Test
  void testAddQueue() {
    testObj.addQueue(adiClubMemberInfoToString());
    AdiClubMemberInfo result = SubscriberQueue.getQueue().poll();
    Assertions.assertEquals(adiClubMemberInfo, result);
  }

  private String adiClubMemberInfoToString() {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .toJson(adiClubMemberInfo);
  }

}
