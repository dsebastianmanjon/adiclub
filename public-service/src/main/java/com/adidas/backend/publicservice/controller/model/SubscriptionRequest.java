package com.adidas.backend.publicservice.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {

  @Schema(description = "The user mail", required = true)
  @Size(max = 200)
  @NotBlank
  @Email(regexp = ".+[@].+[\\.].+")
  private String userMail;

}
