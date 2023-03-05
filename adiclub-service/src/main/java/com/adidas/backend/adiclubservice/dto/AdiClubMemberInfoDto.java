package com.adidas.backend.adiclubservice.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdiClubMemberInfoDto {
  private String email;
  private Integer points;
  private Instant registrationDate;
}
