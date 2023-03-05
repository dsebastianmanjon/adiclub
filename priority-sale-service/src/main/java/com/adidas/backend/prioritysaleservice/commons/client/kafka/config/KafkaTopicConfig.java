package com.adidas.backend.prioritysaleservice.commons.client.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Value("${adidas.kafka.topic.priority-to-queue}")
  private String priorityToQueue;
  @Value("${adidas.kafka.topic.queue-to-priority-request}")
  private String priorityToQueueSendMail;

  /**
   * The topic goes from priority to queue
   * @return NewTopic
   */
  @Bean
  public NewTopic topic1() {
    return TopicBuilder.name(priorityToQueue).build();
  }

  /**
   * The request topic goes from queue to priority
   * @return NewTopic
   */
  @Bean
  public NewTopic topic2() {
    return TopicBuilder.name(priorityToQueueSendMail).build();
  }

}
