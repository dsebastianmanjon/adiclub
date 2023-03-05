package com.adidas.backend.prioritysaleservice.facade.priority;

import com.adidas.backend.prioritysaleservice.commons.model.TypeEngine;
import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;

public interface PrioritySaleFacade {

  /**
   * Get type Engine.
   *
   * @return TypeEngine
   */
  TypeEngine getTypeEngine();

  /**
   * Perform capture action in engine.
   *
   * @param newUserSubscription String
   * @return Optional Long
   */
  AdiClubMemberInfo newSubscriptionAction(String newUserSubscription);
}
