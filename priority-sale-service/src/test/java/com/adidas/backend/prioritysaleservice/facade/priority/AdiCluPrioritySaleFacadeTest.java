package com.adidas.backend.prioritysaleservice.facade.priority;

import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.AdiClubClient;
import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.model.AdiClubMemberInfoResponse;
import com.adidas.backend.prioritysaleservice.exceptions.ResponseErrorException;
import com.adidas.backend.prioritysaleservice.usecase.mapper.AdiClubMemberInfoMapperImpl;
import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AdiCluPrioritySaleFacadeTest {

  private final static String mockedMailAdiClub = "ironmanisreallyhappybecauseisadidasguy@adiclub.com";

  @Mock
  private AdiClubClient adiClubClient;

  private AdiCluPrioritySaleFacade testObj;

  @BeforeEach
  void setup() {
    testObj = new AdiCluPrioritySaleFacade(adiClubClient, new AdiClubMemberInfoMapperImpl());
  }


  @Test
  void testAdiClubMailHappyPath() {
    final Instant now = Instant.now();
    final int points = 1000;
    final AdiClubMemberInfoResponse response = AdiClubMemberInfoResponse.builder()
        .email(mockedMailAdiClub)
        .points(points)
        .registrationDate(now)
        .build();
    Mockito.when(adiClubClient.getSubscriberInfo(mockedMailAdiClub))
        .thenReturn(response);

    AdiClubMemberInfo subscriberInfo = testObj
        .newSubscriptionAction(mockedMailAdiClub);

    Assertions.assertEquals(now, subscriberInfo.getRegistrationDate());
    Assertions.assertEquals(points, subscriberInfo.getPoints());
    Assertions.assertEquals(mockedMailAdiClub, subscriberInfo.getEmail());
  }

  @Test
  void testAdiClubFailFeign() {
    Mockito.when(adiClubClient.getSubscriberInfo(mockedMailAdiClub))
        .thenThrow(new RuntimeException("Testing exception"));

    Assertions.assertThrows(ResponseErrorException.class, () ->
        testObj.newSubscriptionAction(mockedMailAdiClub));
  }

}
