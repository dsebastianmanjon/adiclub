package com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub;

import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.model.AdiClubMemberInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "adiclubClient",
    url = "${client.adiclub.base_url}")
public interface AdiClubClient {

  @GetMapping("/adiclub")
  AdiClubMemberInfoResponse getSubscriberInfo(
      @RequestParam(value = "emailAddress") String subscriber);

}
