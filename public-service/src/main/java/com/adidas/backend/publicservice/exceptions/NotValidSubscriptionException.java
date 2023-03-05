package com.adidas.backend.publicservice.exceptions;

public class NotValidSubscriptionException extends RuntimeException {

  /** Serial version id. */
  private static final long serialVersionUID = -1904674372976012002L;

  /**
   * Not valid subscription exception constructor
   * @param message message
   */
  public NotValidSubscriptionException(String message) {
    super(message);
  }

}
