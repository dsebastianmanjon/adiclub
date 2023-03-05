package com.adidas.backend.prioritysaleservice.commons.client.kafka.model;

public enum QueueMessages {

  /**
   * It sends the winner email from the queue
   */
  TIME_TO_SEND_MAIL,

  /**
   * It clears the queue
   */
  CLEAR

}
