package com.adidas.backend.publicservice.exceptions;

public class ResponseErrorException extends RuntimeException {

  private static final long serialVersionUID = 5115367841079151295L;

  /**
   * Response error exception contructor
   * @param message The exception message
   */
  public ResponseErrorException(String message) {
    super(message);
  }

}
