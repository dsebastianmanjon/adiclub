package com.adidas.backend.queueservice.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.adidas.backend.queueservice.commons.client.kafka.WinnerProducer;
import com.adidas.backend.queueservice.commons.client.kafka.model.QueueMessages;
import com.adidas.backend.queueservice.singleton.SubscriberQueue;
import com.adidas.backend.queueservice.singleton.model.AdiClubMemberInfo;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RecoverAdiClubMemberSendMailUseCaseTest {

  private final static Instant customInstant = Instant.ofEpochMilli(73264271044L);

  @Mock
  private WinnerProducer winnerProducer;

  @InjectMocks
  private RecoverAdiClubMemberSendMailUseCase testObj;

  @Test
  void testRecoverMemberElementFromQueue() {
    testClearQueue();
    SubscriberQueue.getQueue().add(AdiClubMemberInfo.builder()
        .email("a")
        .points(1)
        .registrationDate(customInstant)
        .build());
    Assertions.assertEquals(1, SubscriberQueue.getQueue().size());

    testObj.runOperation(QueueMessages.TIME_TO_SEND_MAIL.name());
    verify(winnerProducer, times(1))
        .send("{\"email\":\"a\",\"points\":1,\"registrationDate\":73264271044}");
  }

  @Test
  void testRecoverMemberElementFromEmptyQueue() {
    testClearQueue();
    testObj.runOperation(QueueMessages.TIME_TO_SEND_MAIL.name());
    Assertions.assertEquals(0, SubscriberQueue.getQueue().size());
    verify(winnerProducer, times(0))
        .send(any());
  }

  @Test
  void testClearQueue() {
    SubscriberQueue.getQueue().add(AdiClubMemberInfo.builder().build());
    Assertions.assertEquals(1, SubscriberQueue.getQueue().size());

    testObj.runOperation(QueueMessages.CLEAR.name());
    Assertions.assertEquals(0, SubscriberQueue.getQueue().size());
  }

}
