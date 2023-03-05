package com.adidas.backend.queueservice.singleton.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AdiClubMemberInfo implements Comparable<AdiClubMemberInfo> {
  private String email;
  private Integer points;
  private Instant registrationDate;

  /**
   * It compares AdiClubMemberInfo objects according points. If points are equals it will be compared using oldest registration
   * @param source the object to be compared.
   * @return 0 equals; 1 this object is greater than source; -1 this object is smaller than source
   */
  @Override
  public int compareTo(AdiClubMemberInfo source) {
    if (this.getPoints() < source.getPoints()) return 1;
    else if(this.getPoints() > source.getPoints()) return -1;
    else return this.getRegistrationDate().compareTo(source.getRegistrationDate()) < 0
          ? -1
          : 1;
  }
}
