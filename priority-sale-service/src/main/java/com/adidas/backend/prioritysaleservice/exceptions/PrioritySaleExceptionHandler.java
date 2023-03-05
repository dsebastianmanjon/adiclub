package com.adidas.backend.prioritysaleservice.exceptions;

import com.adidas.backend.prioritysaleservice.controller.PrioritySaleRestController;
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
@ControllerAdvice(assignableTypes = {PrioritySaleRestController.class})
public class PrioritySaleExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String EXCEPTION = "Exception: ";
  private static final String TIMESTAMP = "timestamp";
  private static final String MESSAGE = "message";


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
    body.put(MESSAGE, message);

    return body;
  }

}
