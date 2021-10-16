package ca.nomosnow.sport_event_service.service;

import ca.nomosnow.sport_event_service.configuration.ConfigService;
import ca.nomosnow.sport_event_service.domain.model.SportEvent;
import ca.nomosnow.sport_event_service.domain.model.SportOrganization;
import ca.nomosnow.sport_event_service.repository.SportEventRepository;
import ca.nomosnow.sport_event_service.service.client.SportOrganizationDiscoveryClient;
import ca.nomosnow.sport_event_service.service.client.SportOrganizationFeignClient;
import ca.nomosnow.sport_event_service.service.client.SportOrganizationRestTemplateClient;
import ca.nomosnow.sport_event_service.utils.UserContext;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeoutException;


/*
SportEventService create command and invoke SportEvent functions
 */
// need to add this annotation to have @Autowire
@Service
public class SportEventService {
    private static final Logger logger = LoggerFactory.getLogger(SportEventService.class);
    @Autowired
    MessageSource messageSource;
    @Autowired
    SportEventRepository sportEventRepository;
    @Autowired
    ConfigService configService;
    @Autowired
    SportOrganizationFeignClient sportOrganizationFeignClient;
    @Autowired
    SportOrganizationDiscoveryClient sportOrganizationDiscoveryClient;
    @Autowired
    SportOrganizationRestTemplateClient sportOrganizationRestTemplateClient;

    /**
     *
     * Method find SportEvent by Id and organization id
     * @param organizationId String
     * @param sportEventId String
     * @return SportEvent Object
     */

    public SportEvent getSportEvent(String organizationId, String sportEventId, Locale locale ) {
        SportEvent sportEvent = sportEventRepository.findByOrganizationIdAndSportEventId(organizationId, sportEventId);
        if (sportEvent == null) {
            System.out.printf(messageSource.getMessage("sportEvent.search.error.message", null, locale), sportEventId, organizationId);
            throw new IllegalArgumentException(String.format(messageSource.getMessage("sportEvent.search.error.message",null,locale),sportEventId,organizationId));
        }
        System.out.printf(messageSource.getMessage("sportEvent.get.message",null,locale),sportEvent.toString());

        return sportEvent.withComment(configService.getProperty());
    }

    /**
     * Method create SportEvent by passing body from client
     * @param sportEvent SportEvent object
     * @return SportEvent with config property from another service
     */
    public SportEvent createSportEvent(SportEvent sportEvent,Locale locale) {
        sportEvent.setSportEventId(UUID.randomUUID().toString());
        sportEventRepository.save(sportEvent);
        System.out.printf(messageSource.getMessage("sportEvent.create.message",null,locale),sportEvent.toString());
        return sportEvent.withComment(configService.getProperty());
    }

    /**
     * Method update SportEvent by passing body from client
     * @param sportEvent SportEvent object
     * @return SportEvent with config property from another servce
     */
    public SportEvent updateSportEvent(SportEvent sportEvent,Locale locale){
        sportEventRepository.save(sportEvent);
        System.out.printf(messageSource.getMessage("sportEvent.update.message",null,locale),sportEvent.toString());
        return sportEvent.withComment(configService.getProperty());
    }
    /**
     * temporary(need fix)
     * @param id String
     * @return String message
     */
    public String deleteSportEvent(String id,Locale locale) {
        SportEvent event = new SportEvent();
        event.setSportEventId(id);
        sportEventRepository.delete(event);
        return String.format(messageSource.getMessage("sportEvent.delete.message",null,locale),id);
    }


    public SportEvent getSportEventWithClient(String sportEventId, String organizationId,Locale locale, String clientType){
        SportEvent sportEvent = sportEventRepository.findByOrganizationIdAndSportEventId(organizationId, sportEventId);
        if (null == sportEvent) {
            throw new IllegalArgumentException(String.format(
                    messageSource.getMessage("sportEvent.search.error.message", null, locale),
                    sportEventId, organizationId));
        }
        SportOrganization sportOrganization = retrieveOrganizationInfo(organizationId,
                clientType);
        if (sportOrganization != null) {
            sportEvent.setSportOrganizationName(sportOrganization.getSportOrganizationName());
            sportEvent.setSportOrganizationPhone(sportOrganization.getSportOrganizationPhone());
            sportEvent.setSportOrganizationEmail(sportOrganization.getSportOrganizationEmail());

        }
        return sportEvent.withComment(configService.getProperty());
    }
    private SportOrganization retrieveOrganizationInfo(String organizationId, String clientType) {
        SportOrganization organization;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = sportOrganizationFeignClient.getSportOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = sportOrganizationRestTemplateClient.getSportOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = sportOrganizationDiscoveryClient.getSportOrganization(organizationId);
                break;
            default:
                organization = sportOrganizationRestTemplateClient.getSportOrganization(organizationId);
        }
        return organization;
    }

    @CircuitBreaker(name="sportEventService",
                    fallbackMethod = "fallbackMethodForCircuitBreaker")

//    @RateLimiter(name ="sportEventService",
//            fallbackMethod = "fallbackMethodForCircuitBreaker")
//    @Retry(name="retrySportEventService",
//            fallbackMethod= "fallbackMethodForCircuitBreaker")
//    @Bulkhead(name = "bulkheadSportEventService",
//            //                 type default  of bulkhead is semaphore//
//            type= Bulkhead.Type.THREADPOOL,
//            fallbackMethod="fallbackMethodForCircuitBreaker")
    public List<SportEvent> getSportEventsByOrganizationId(String organizationId) {
        logger.debug("Get List of SportEvents by Organization with Correlation Id: {}", UserContext.getCorrelationId());
//        randomlyRunLong();
        return sportEventRepository.findByOrganizationId(organizationId);
    }


    public void approved(String sportEventId) {

    }

    /**
     * Fallback method when circuitBreaker open
     * @param organizationId String organization Id
     * @return List
     */
    public List<SportEvent> fallbackMethodForCircuitBreaker(String organizationId, Throwable t) {
        List<SportEvent> fallBackList = new ArrayList<>();
        SportEvent sportEvent = new SportEvent();
        sportEvent.setSportEventId("00000000-0000-0000-0000-000000000000");
        sportEvent.setOrganizationId(organizationId);
        fallBackList.add(sportEvent);
        return fallBackList;
    }


    // this 2 function use to test circuit breaker by create status 500 FAIL with timeoutException
    private void randomlyRunLong() throws TimeoutException {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum==3) sleep();
    }
    private void sleep() throws TimeoutException {
        try {
            System.out.println("Sleep");
            Thread.sleep(5000);
            throw new java.util.concurrent.TimeoutException();
        } catch (InterruptedException e ) {
            logger.error(e.getMessage());
        }
    }

}
