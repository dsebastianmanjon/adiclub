package com.adidas.backend.prioritysaleservice.usecase.model;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdiClubMemberInfo implements Serializable {

  private String email;
  private Integer points;
  private Instant registrationDate;

}
