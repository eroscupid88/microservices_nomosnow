package ca.nomosnow.sport_event_service.service;

import ca.nomosnow.sport_event_service.config.ConfigService;
import ca.nomosnow.sport_event_service.model.SportEvent;
import ca.nomosnow.sport_event_service.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


import java.util.Locale;
import java.util.UUID;

import io.github.cdimascio.dotenv.*;

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
     * Method find SportEvent by Id
     * @param id String
     * @return SportEvent Object
     */
    public SportEvent getSportEvent(String id ) {
        SportEvent sportEvent = sportEventRepository.findSportEventBySportEventId(id);
        return sportEvent;
    }


    /**
     * Method create SportEvent by passing body from client
     * @param sportEvent SportEvent object
     * @return SportEvent with config property from another service
     */
    public SportEvent createSportEvent(SportEvent sportEvent) {
        sportEvent.setSportEventId(UUID.randomUUID().toString());
        sportEventRepository.save(sportEvent);
        return sportEvent.withComment(configService.getProperty());
    }

    /**
     * Method update SportEvent by passing body from client
     * @param sportEvent SportEvent object
     * @return SportEvent with config property from another servce
     */
    public SportEvent updateSportEvent(SportEvent sportEvent){

        sportEventRepository.save(sportEvent);
        return sportEvent.withComment(configService.getProperty());
    }

    /**
     * temporarry(need fix)
     * @param id String
     * @return String message
     */
    public String deleteSportEvent(String id) {
        SportEvent event = new SportEvent();
        event.setSportEventId(id);
        sportEventRepository.delete(event);
        return String.format(messageSource.getMessage("sportEvent.delete.message",null,null),id);
    }



}
