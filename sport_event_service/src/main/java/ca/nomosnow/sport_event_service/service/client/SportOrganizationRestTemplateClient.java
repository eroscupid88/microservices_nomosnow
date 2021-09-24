package ca.nomosnow.sport_event_service.service.client;

import ca.nomosnow.sport_event_service.model.SportOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// the load balancer enable rest template, parse url into the template default round-robin all instances of
// organization service
@Component
public class SportOrganizationRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public SportOrganization getSportOrganization(String organizationId){
        ResponseEntity<SportOrganization> restExchange =
                restTemplate.exchange(
                        "http://sport-organization-service/v1/sportOrganization/{organizationId}",
                        HttpMethod.GET,
                        null, SportOrganization.class, organizationId);

        return restExchange.getBody();
    }
}


