package com.adidas.backend.publicservice.usecase;

import static org.mockito.ArgumentMatchers.any;

import com.adidas.backend.publicservice.commons.client.priority.PrioritySaleClient;
import com.adidas.backend.publicservice.exceptions.ResponseErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class SubscriptionUseCaseTest {

  private static final String mail = "iamscarletspiderman@adiclub.com";

  @Mock
  private PrioritySaleClient prioritySaleClient;

  @InjectMocks
  private SubscriptionUseCase testObj;

  @Test
  void testCreateSubscriptionHappyPath() {
    Assertions.assertDoesNotThrow(() ->
        testObj.createSubscription(mail));
  }

  @Test
  void testCreateSubscriptionFeignClientException() {
    Mockito.when(prioritySaleClient.addNewUser(any()))
        .thenThrow(new RuntimeException("Testing exception"));

    Assertions.assertThrows(ResponseErrorException.class, ()->
        testObj.createSubscription(mail));
  }

}
