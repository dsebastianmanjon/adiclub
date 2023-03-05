package com.adidas.backend.prioritysaleservice.commons;

import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class InstantTypeConverterTest {

  private static final String mockedMail = "jhondueisnotblue@adiclub.com";
  private static final Integer mockedPoints = 1;
  private static final Instant mockedRegistrationDate = Instant.now();

  @Test
  void testSerialize() {
    final String testJson = new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .toJson(createAdiClubMemberInfo());

    Assertions.assertEquals(createAdiClubMemberJson(), testJson);
  }

  @Test
  void testDeserialize() {
    AdiClubMemberInfo testAdiClubMemberInfo = new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .fromJson(createAdiClubMemberJson(), AdiClubMemberInfo.class);

    final AdiClubMemberInfo expectedObject = createAdiClubMemberInfo();
    Assertions.assertEquals(expectedObject.getEmail(), testAdiClubMemberInfo.getEmail());
    Assertions.assertEquals(expectedObject.getPoints(), testAdiClubMemberInfo.getPoints());
    Assertions.assertEquals(expectedObject.getRegistrationDate().getEpochSecond(), testAdiClubMemberInfo.getRegistrationDate().getEpochSecond());
  }

  private AdiClubMemberInfo createAdiClubMemberInfo() {
    return AdiClubMemberInfo.builder()
        .email(mockedMail)
        .points(mockedPoints)
        .registrationDate(mockedRegistrationDate)
        .build();
  }

  private String createAdiClubMemberJson() {
    return "{\"email\":\""
        + mockedMail
        + "\",\"points\":"
        + mockedPoints
        + ",\"registrationDate\":"
        + mockedRegistrationDate.toEpochMilli()
        + "}";
  }

}
