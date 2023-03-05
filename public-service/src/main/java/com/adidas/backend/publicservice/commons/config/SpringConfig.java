package com.adidas.backend.publicservice.commons.config;

import com.adidas.backend.publicservice.commons.client.priority.PrioritySaleClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {PrioritySaleClient.class})
public class SpringConfig {

}
