package com.adidas.backend.prioritysaleservice.commons.config;

import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.AdiClubClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableFeignClients(clients = {AdiClubClient.class})
public class SpringConfig {

}
