package com.adidas.backend.publicservice.exceptions;

import com.adidas.backend.publicservice.controller.SubscriptionRestController;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(assignableTypes = {SubscriptionRestController.class})
public class SubscriptionExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String EXCEPTION = "Exception: ";
  private static final String TIMESTAMP = "timestamp";

  /**
   * Invalid mail direction
   *
   * @param exception subscription invalid
   * @return ErrorMessage
   */
  @ExceptionHandler(NotValidSubscriptionException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseEntity<Object> cancelOrderException(NotValidSubscriptionException exception) {
    log.warn(EXCEPTION, exception);
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(errorMessage(exception.getMessage()));
  }

  /**
   * Empty response from AdiClub error exception
   *
   * @param exception subscription with error
   * @return ErrorMessage
   */
  @ExceptionHandler(ResponseErrorException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseEntity<Object> responseErrorException(ResponseErrorException exception) {
    log.warn(EXCEPTION, exception);
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(errorMessage(exception.getMessage()));
  }

  private Map<String, Object> errorMessage(String message) {
    final Map<String, Object> body = new LinkedHashMap<>();

    body.put(TIMESTAMP, LocalDateTime.now());
    log.error(message);

    return body;
  }

}
