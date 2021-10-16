package ca.nomosnow.sport_event_service.service.client;

import ca.nomosnow.sport_event_service.domain.model.SportOrganization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("sport-organization-service")
public interface SportOrganizationFeignClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/sportOrganization/{sportOrganizationId}",
            consumes = "application/json")
    SportOrganization getSportOrganization(@PathVariable("sportOrganizationId") String organization);


}
