package com.adidas.backend.emailservice.usecase;

import com.adidas.backend.emailservice.commons.InstantTypeConverter;
import com.adidas.backend.emailservice.usecase.model.AdiClubMemberInfo;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class WinnerMailUseCase {

  /**
   * It sends the winner message to the proper mail
   * @param data The jSon AdiClubMemberInfo input
   */
  public void sendMail(String data) {
    AdiClubMemberInfo memberWinner = parseToObject(data);
    log.info("User winner {}, point {}, registered {}",
        memberWinner.getEmail(), memberWinner.getPoints(), memberWinner.getRegistrationDate());
    sendMail(memberWinner);
  }

  private AdiClubMemberInfo parseToObject(String newAdiClubUser) {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantTypeConverter())
        .create()
        .fromJson(newAdiClubUser, AdiClubMemberInfo.class);
  }

  private void sendMail(AdiClubMemberInfo memberWinner) {
    log.info("Email sent to {}", memberWinner.getEmail());
  }

}
