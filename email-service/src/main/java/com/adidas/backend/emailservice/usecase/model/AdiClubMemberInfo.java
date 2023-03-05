package com.adidas.backend.emailservice.usecase.model;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdiClubMemberInfo implements Serializable {

  private String email;
  private Integer points;
  private Instant registrationDate;

}
