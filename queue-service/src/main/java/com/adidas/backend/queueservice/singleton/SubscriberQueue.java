package com.adidas.backend.queueservice.singleton;

import com.adidas.backend.queueservice.singleton.model.AdiClubMemberInfo;
import java.util.PriorityQueue;

public class SubscriberQueue {

  private static PriorityQueue<AdiClubMemberInfo> queue;

  private SubscriberQueue() {
    throw new IllegalStateException("Subscriber queue");
  }


  /**
   * The singleton that it contains the queue
   * @return PriorityQueue<AdiClubMemberInfo> queue
   */
  public static PriorityQueue<AdiClubMemberInfo> getQueue() {
    if (queue == null) {
      queue = new PriorityQueue<>();
    }

    return queue;
  }


}
