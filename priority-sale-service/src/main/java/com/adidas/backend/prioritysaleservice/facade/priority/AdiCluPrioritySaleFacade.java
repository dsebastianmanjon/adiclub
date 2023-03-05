package com.adidas.backend.prioritysaleservice.facade.priority;

import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.AdiClubClient;
import com.adidas.backend.prioritysaleservice.commons.model.TypeEngine;
import com.adidas.backend.prioritysaleservice.exceptions.ResponseErrorException;
import com.adidas.backend.prioritysaleservice.usecase.mapper.AdiClubMemberInfoMapper;
import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdiCluPrioritySaleFacade implements PrioritySaleFacade {

  private final AdiClubClient adiClubClient;
  private final AdiClubMemberInfoMapper mapper;

  /**
   * It recovers the engine for this facade
   * @return TypeEngine ADICLUB
   */
  @Override
  public TypeEngine getTypeEngine() {
    return TypeEngine.ADICLUB;
  }

  /**
   * The new subscription action for adiClub
   * @param newUserSubscription String
   * @return AdiClubMemberInfo
   */
  @Override
  public AdiClubMemberInfo newSubscriptionAction(String newUserSubscription) {
    log.info("Initializing {} for new {} subscription", TypeEngine.ADICLUB, newUserSubscription);

    try{
      return mapper.map(
          adiClubClient.getSubscriberInfo(newUserSubscription));
    } catch (Exception e) {
      throw new ResponseErrorException("Error calling to AdiClub: " + e.getLocalizedMessage());
    }

  }

}
