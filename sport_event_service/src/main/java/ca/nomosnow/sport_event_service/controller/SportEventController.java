package ca.nomosnow.sport_event_service.controller;


import ca.nomosnow.sport_event_service.model.SportEvent;
import ca.nomosnow.sport_event_service.repository.SportEventRepository;
import ca.nomosnow.sport_event_service.service.SportEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * SportEventController map API and return response json
 */
@RestController
@RequestMapping(value = {"/v1/sportOrganization/{sportOrganizationId}/sportEvent"})
public class SportEventController {
    //    Log any event with Controller
    private static final Logger logger = LoggerFactory.getLogger(SportEventController.class);
    // autowired SportEventService class
    @Autowired
    private SportEventService sportEventService;


    //Get Method and href

    /**
     * getSportEvent method return HTTP response
     * @param organizationId path variable
     * @param id sportEventId path variable
     * @return HTTP response SportEvent Object contain headers, body and status code of SportEvent object
     */
    @GetMapping(value = "/{sportEventId}")
    public ResponseEntity<SportEvent> getSportEvent(@PathVariable("sportOrganizationId")String organizationId,
                                                    @PathVariable("sportEventId") String id,
                                                    @RequestHeader(value = "Accept-Language",required = false) Locale locale) {
            SportEvent sportEvent = sportEventService.getSportEvent(organizationId,id,locale);
            return Optional.ofNullable(sportEvent)
                            .map(user -> ResponseEntity.ok().body(user
                                    .add(linkTo(methodOn(SportEventController.class)
                                                    .getSportEvent(organizationId,id,locale))
                                                    .withSelfRel(),
                                                    linkTo(methodOn(SportEventController.class)
                                                    .createSportEvent(sportEvent,locale))
                                                    .withRel("createSportEvent"),
                                                    linkTo(methodOn(SportEventController.class)
                                                    .updateSportEvent(sportEvent,locale))
                                                    .withRel("updateSportEvent"))))
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/{sportEventId}/{clientType}",method = RequestMethod.GET)
    public SportEvent getSportEventWithClient(@PathVariable("sportEventId") String sportEventId,
                                              @PathVariable("sportOrganizationId") String organizationId,
                                              @RequestHeader(value = "Accept-Language",required = false) Locale locale,
                                              @PathVariable("clientType") String clientType) {

        return sportEventService.getSportEventWithClient(sportEventId, organizationId, locale, clientType);
    }

    //Post method
    /**
     * createSportEvent method pass request body sportEvent and return HTTP response
     * @param sportEvent from request body
     * @return HTTP response SportEvent Object with status codes, headers and body of sportEvent
     */
    @PostMapping()
    public ResponseEntity<SportEvent> createSportEvent(
            @RequestBody SportEvent sportEvent,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale) {
        return ResponseEntity.ok(sportEventService.createSportEvent(sportEvent,locale));
    }
    //Put Method
    @PutMapping
    /**
     * updateSportEvent method pass request body, update sportEvent and return HTTP response
     * @param sportEvent from request body
     * @return HTTP response SportEvent Object with status codes, headers and body of sportEvent
     */
    public ResponseEntity<SportEvent>updateSportEvent(
            @RequestBody SportEvent sportEvent,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale){
        return ResponseEntity.ok(sportEventService.updateSportEvent(sportEvent,locale));
    }

    /**
     * delete sportEvent with given Id
     * @param sportEventId String
     * @return HTTP response String with status codes, headers and body of sportEvent
     *
     */
    @DeleteMapping(value="/{sportEventId}")
    public ResponseEntity<String> deleteSportEvent(@PathVariable("sportEventId") String sportEventId,
                                                   @RequestHeader(value = "Accept-Language",required = false) Locale locale)
    {
        return ResponseEntity.ok(sportEventService.deleteSportEvent(sportEventId,locale));
    }
}


