package com.adidas.backend.prioritysaleservice.facade.priority;

import com.adidas.backend.prioritysaleservice.commons.model.TypeEngine;
import java.util.EnumMap;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrioritySaleFacadeFactory {

  @Value("${adidas.adi_club}")
  private String adiClub;

  private static final EnumMap<TypeEngine, PrioritySaleFacade> facadeCache =
      new EnumMap<>(TypeEngine.class);

  private final List<PrioritySaleFacade> facades;

  /**
   * The factory for the different facade engines
   * @param newMailSubscription The input subscriber
   * @return PrioritySaleFacade
   */
  public PrioritySaleFacade getPrioritySaleFacade(String newMailSubscription) {
    final TypeEngine typeEngine = recoverTypeEngine(newMailSubscription);
    log.info("Getting facade implementation for type {}", typeEngine);
    return facadeCache.get(typeEngine);
  }

  @PostConstruct
  private void initCache() {
    for (PrioritySaleFacade facade : facades) {
      facadeCache.put(facade.getTypeEngine(), facade);
    }
  }

  private TypeEngine recoverTypeEngine(String newMailSubscription) {
    return newMailSubscription.endsWith(adiClub)
        ? TypeEngine.ADICLUB
        : TypeEngine.OTHER;
  }
}
