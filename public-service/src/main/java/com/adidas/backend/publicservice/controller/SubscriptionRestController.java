package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.commons.OpenApiConstants;
import com.adidas.backend.publicservice.controller.model.SubscriptionRequest;
import com.adidas.backend.publicservice.usecase.SubscriptionUseCase;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = OpenApiConstants.TAG_SUBSCRIPTION)
@RestController
@RequestMapping(value = "/subscription")
@RequiredArgsConstructor
public class SubscriptionRestController {

  private final SubscriptionUseCase subscriptionUseCase;

  /**
   * The rest controller for subscription
   * @param createSubscriptionRequest createSubscriptionRequest
   * @return ResponseEntity<Void>
   */
  @ApiResponses(
      value = {
          @ApiResponse(description = "Created OK", responseCode = "201", content = @Content),
          @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unprocessable entity", responseCode = "422", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      })
  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> createSubscription(
      @RequestBody @Valid SubscriptionRequest createSubscriptionRequest) {

    subscriptionUseCase.createSubscription(createSubscriptionRequest.getUserMail());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
