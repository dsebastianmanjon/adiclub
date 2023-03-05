package com.adidas.backend.prioritysaleservice.usecase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.AdiClubClient;
import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.model.AdiClubMemberInfoResponse;
import com.adidas.backend.prioritysaleservice.commons.client.kafka.QueueProducer;
import com.adidas.backend.prioritysaleservice.commons.model.TypeEngine;
import com.adidas.backend.prioritysaleservice.exceptions.ResponseErrorException;
import com.adidas.backend.prioritysaleservice.facade.priority.AdiCluPrioritySaleFacade;
import com.adidas.backend.prioritysaleservice.facade.priority.OtherPrioritySaleFacade;
import com.adidas.backend.prioritysaleservice.facade.priority.PrioritySaleFacade;
import com.adidas.backend.prioritysaleservice.facade.priority.PrioritySaleFacadeFactory;
import com.adidas.backend.prioritysaleservice.usecase.mapper.AdiClubMemberInfoMapperImpl;
import java.time.Instant;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
class PrioritySaleUseCaseTest {

  private final static String mockedMailAdiClub = "adidasdesigntheironspidertonystarkdidnot@adiclub.com";
  private final static String mockedMailNonAdiClub = "americacapteinwantstobeadidas@gmail.com";
  private final static Instant customInstant = Instant.ofEpochMilli(73264271044L);

  private PrioritySaleUseCase testObj;

  @Mock
  private AdiClubClient adiClubClient;
  @Mock
  private QueueProducer queueProducer;


  @BeforeEach
  void setup() {
    final AdiCluPrioritySaleFacade adiCluPrioritySaleFacade
        = new AdiCluPrioritySaleFacade(adiClubClient, new AdiClubMemberInfoMapperImpl());
    final OtherPrioritySaleFacade otherPrioritySaleFacade
        = new OtherPrioritySaleFacade();

    final PrioritySaleFacadeFactory prioritySaleFacadeFactory
        = new PrioritySaleFacadeFactory(Arrays.asList(adiCluPrioritySaleFacade, otherPrioritySaleFacade));

    testObj = new PrioritySaleUseCase(prioritySaleFacadeFactory, queueProducer);
    initByReflection(prioritySaleFacadeFactory, adiCluPrioritySaleFacade, otherPrioritySaleFacade);
  }

  @Test
  void testNonAdiClubHappyPath() {
    try (MockedStatic<Instant> mocked = Mockito.mockStatic(Instant.class)) {
      mocked.when(Instant::now).thenReturn(customInstant);
      testObj.receivedNewSubscription(mockedMailNonAdiClub);
    }
    verify(queueProducer, times(1))
        .send("{\"email\":\"americacapteinwantstobeadidas@gmail.com\",\"points\":0,\"registrationDate\":73264271044}");
  }

  @Test
  void testAdiClubHappyPath() {
    Mockito.when(adiClubClient.getSubscriberInfo(mockedMailAdiClub))
            .thenReturn(AdiClubMemberInfoResponse.builder()
                .email(mockedMailAdiClub)
                .points(10000)
                .registrationDate(customInstant)
                .build());

    testObj.receivedNewSubscription(mockedMailAdiClub);
    verify(queueProducer, times(1))
        .send("{\"email\":\"adidasdesigntheironspidertonystarkdidnot@adiclub.com\",\"points\":10000,\"registrationDate\":73264271044}");
  }

  @Test
  void testAdiClubWrongFeignCall() {
    Mockito.when(adiClubClient.getSubscriberInfo(mockedMailAdiClub))
            .thenThrow(new ResponseErrorException("Testing mock"));

    Assertions.assertThrows(ResponseErrorException.class, () ->
        testObj.receivedNewSubscription(mockedMailAdiClub));
  }

  private void initByReflection(PrioritySaleFacadeFactory prioritySaleFacadeFactory,
      AdiCluPrioritySaleFacade adiCluPrioritySaleFacade,
      OtherPrioritySaleFacade otherPrioritySaleFacade) {
    ReflectionTestUtils.setField(
        prioritySaleFacadeFactory,
        "adiClub",
        "adiclub.com");

    @SuppressWarnings("unchecked")
    EnumMap<TypeEngine, PrioritySaleFacade> facadeCache = ((EnumMap<TypeEngine, PrioritySaleFacade>)
        ReflectionTestUtils.getField(prioritySaleFacadeFactory, "facadeCache"));
    Objects.requireNonNull(facadeCache).put(TypeEngine.ADICLUB, adiCluPrioritySaleFacade);
    Objects.requireNonNull(facadeCache).put(TypeEngine.OTHER, otherPrioritySaleFacade);
  }

}
