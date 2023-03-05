package com.adidas.backend.prioritysaleservice.facade.priority;

import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class OtherPrioritySaleFacadeTest {

  private final static String mockedMailNonAdiClub = "capitanamericareallylovesadidas@gmail.com";

  private final OtherPrioritySaleFacade testObj = new OtherPrioritySaleFacade();


  @Test
  void testOtherMailHappyPath() {
    AdiClubMemberInfo subscriberInfo = testObj
        .newSubscriptionAction(mockedMailNonAdiClub);

    Assertions.assertEquals(0, subscriberInfo.getPoints());
    Assertions.assertEquals(mockedMailNonAdiClub, subscriberInfo.getEmail());
  }

}
