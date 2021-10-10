package ca.nomosnow.sport_event_service.service.client;

import ca.nomosnow.sport_event_service.model.SportOrganization;
import ca.nomosnow.sport_event_service.repository.OrganizationRedisRepository;
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
    OrganizationRedisRepository redisRepository;
    private static final Logger logger = LoggerFactory.getLogger(SportOrganizationRestTemplateClient.class);

    public SportOrganization getSportOrganization(String organizationId){
        logger.debug("in Sport-event-service.getOrganization: {}", UserContext.getCorrelationId());

        SportOrganization sportOrganization = checkRedisCache(organizationId);
        if (sportOrganization != null) {
            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, sportOrganization);
            return sportOrganization;
        }
        logger.debug("Unable to locate organization from the redis cache: {}.",organizationId);
        ResponseEntity<SportOrganization> restExchange =
                restTemplate.exchange(
                        "http://gateway:7072/organization/v1/sportOrganization/{organizationId}",
                        HttpMethod.GET,
                        null, SportOrganization.class, organizationId);
        sportOrganization = restExchange.getBody();
        if (sportOrganization != null) {
            cacheOrganizationObject(sportOrganization);
        }
        return restExchange.getBody();
    }


    /**
     * Method checkRedisCache to check and return SportOrganization. return null if there is none
     * @param sportOrganizationId sport organization id
     * @return SportOrganization if found, null otherwise
     */
    private SportOrganization checkRedisCache(String sportOrganizationId) {
        try {
            return redisRepository
                    .findById(sportOrganizationId)
                    .orElse(null);
        }catch (Exception ex){
            logger.error("Error encountered while trying to retrieve organization{} check Redis Cache.  Exception {}",
                    sportOrganizationId, ex);
            return null;
        }
    }
    /**
     * cacheOrganizationObject method save SportOrganization Object into cache if there is any change
     * @param sportOrganization SportOrganization object
     */
    private void cacheOrganizationObject(SportOrganization sportOrganization) {
        try {
            redisRepository.save(sportOrganization);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}",
                    sportOrganization.getId(), ex);
        }
    }

}


