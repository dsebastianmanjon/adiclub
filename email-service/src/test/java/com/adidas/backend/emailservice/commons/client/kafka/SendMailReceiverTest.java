package com.adidas.backend.emailservice.commons.client.kafka;

import com.adidas.backend.emailservice.commons.InstantTypeConverter;
import com.adidas.backend.emailservice.usecase.WinnerMailUseCase;
import com.adidas.backend.emailservice.usecase.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
class SendMailReceiverTest {

  private static final String mockedMail = "jhondueisnotblue@adiclub.com";

  private SendMailReceiver testObj;

  @BeforeEach
  void setup() {
    testObj = new SendMailReceiver(new WinnerMailUseCase());
  }

  @Test
  void receiveMessageFromKafka(CapturedOutput output) {
    testObj.receive(createMockedInputFromKafka());
    Assertions.assertTrue(output.getOut().contains("Email sent to " + mockedMail));
  }

  private String createMockedInputFromKafka() {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .toJson(AdiClubMemberInfo.builder()
            .email(mockedMail)
            .points(1)
            .registrationDate(Instant.now())
            .build());
  }

}
