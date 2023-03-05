package com.adidas.backend.publicservice.commons.client.priority;

import com.adidas.backend.publicservice.commons.client.priority.model.SubscriptionClientRequest;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "priorityClient",
    url = "${client.priority_sale.base_url}")
public interface PrioritySaleClient {

  @PostMapping("/priority")
  ResponseEntity<Void> addNewUser(
      @RequestBody @Valid SubscriptionClientRequest request);

}
