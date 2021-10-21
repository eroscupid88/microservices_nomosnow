package ca.nomosnow.sport_event_service.service.client;

import ca.nomosnow.sport_event_service.domain.model.SportOrganization;
import ca.nomosnow.sport_event_service.redisCache.RedisCacheSupport;
import ca.nomosnow.sport_event_service.utils.UserContext;
//import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// the load balancer enable rest template, parse url into the template default round-robin all instances of
// organization service
@Component
public class SportOrganizationRestTemplateClient {
//    @Autowired
//    KeycloakRestTemplate restTemplate;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisCacheSupport redis;

    private static final Logger logger = LoggerFactory.getLogger(SportOrganizationRestTemplateClient.class);

    public SportOrganization getSportOrganization(String organizationId){
        logger.debug("in Sport-event-service.getOrganization: {}", UserContext.getCorrelationId());

        SportOrganization sportOrganization = redis.OrganizationCheckRedisCache(organizationId);
        if (sportOrganization != null) {
            logger.debug("From SportOrganization rest template : successfully retrieved an organization {} from the redis cache: {}", organizationId, sportOrganization);
            return sportOrganization;
        }
        logger.debug("From SportOrganization rest template : Unable to locate organization from the redis cache: {}.",organizationId);
        ResponseEntity<SportOrganization> restExchange =
                restTemplate.exchange(
                        "http://gatewayserver:7072/organization/v1/sportOrganization/{organizationId}",
                        HttpMethod.GET,
                        null, SportOrganization.class, organizationId);
        sportOrganization = restExchange.getBody();
        if (sportOrganization != null) {
            redis.cacheOrganizationObject(sportOrganization);
        }
        return restExchange.getBody();
    }




}


