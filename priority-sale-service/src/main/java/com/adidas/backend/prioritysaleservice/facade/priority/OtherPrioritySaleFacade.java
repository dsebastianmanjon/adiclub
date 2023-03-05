package com.adidas.backend.prioritysaleservice.facade.priority;

import com.adidas.backend.prioritysaleservice.commons.model.TypeEngine;
import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import java.time.Instant;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class OtherPrioritySaleFacade implements PrioritySaleFacade {

  /**
   * It recovers the engine for this facade
   * @return TypeEngine OTHER
   */
  @Override
  public TypeEngine getTypeEngine() {
    return TypeEngine.OTHER;
  }

  /**
   * The new subscription action for non adiClub
   * @param newUserSubscription String
   * @return AdiClubMemberInfo
   */
  @Override
  public AdiClubMemberInfo newSubscriptionAction(String newUserSubscription) {
    log.info("Initializing {} for new {} for non adiClub subscription", TypeEngine.OTHER, newUserSubscription);

    return AdiClubMemberInfo.builder()
        .email(newUserSubscription)
        .points(0)
        .registrationDate(Instant.now())
        .build();
  }
}
