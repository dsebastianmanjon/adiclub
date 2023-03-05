package com.adidas.backend.prioritysaleservice.exceptions;

public class ResponseErrorException extends RuntimeException {

  private static final long serialVersionUID = -7299771598625364466L;

  /**
   * The response error exception
   * @param message The message
   */
  public ResponseErrorException(String message) {
    super(message);
  }

}
