package ca.nomosnow.sport_event_service.service.client;

import ca.nomosnow.sport_event_service.model.SportOrganization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SportOrganizationDiscoveryClient  {
    @Autowired
    DiscoveryClient discoveryClient;

    public SportOrganization getSportOrganization(String sportOrganizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("sport-organization-service");
        if (instances.size() == 0) return null;
        String serviceUri = String.format("%s/v1/organization/%s",instances.get(0).getUri());
        ResponseEntity<SportOrganization> restExchange = restTemplate.exchange(serviceUri,
                HttpMethod.GET,
                null,
                SportOrganization.class,
                sportOrganizationId);
        return restExchange.getBody();
    }
}
