package ca.nomosnow.sport_event_service.service;

import ca.nomosnow.sport_event_service.config.ConfigService;
import ca.nomosnow.sport_event_service.model.SportEvent;
import ca.nomosnow.sport_event_service.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


import java.util.Locale;
import java.util.UUID;


/*
SportEventService create command and invoke SportEvent functions
 */
// need to add this annotation to have @Autowire
@Service
public class SportEventService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    SportEventRepository sportEventRepository;
    @Autowired
    ConfigService configService;


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
            throw new IllegalArgumentException(String.format(messageSource.getMessage("sportEvent.search.error.message",null,locale),sportEventId,organizationId));
        }
        System.out.print(String.format(messageSource.getMessage("sportEvent.get.message",null,locale),sportEvent.toString()));
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
        System.out.print(String.format(messageSource.getMessage("sportEvent.create.message",null,locale),sportEvent.toString()));
        return sportEvent.withComment(configService.getProperty());
    }

    /**
     * Method update SportEvent by passing body from client
     * @param sportEvent SportEvent object
     * @return SportEvent with config property from another servce
     */
    public SportEvent updateSportEvent(SportEvent sportEvent,Locale locale){
        sportEventRepository.save(sportEvent);
        System.out.print(String.format(messageSource.getMessage("sportEvent.update.message",null,locale),sportEvent.toString()));
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



}
