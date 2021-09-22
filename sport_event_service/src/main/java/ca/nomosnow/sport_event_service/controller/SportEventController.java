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
@RequestMapping(value = {"v1/sportOrganization/{sportOrganizationId}/sportEvent"})
public class SportEventController {
    //    Log any event with Controller
    private static final Logger logger = LoggerFactory.getLogger(SportEventController.class);
    // autowired SportEventService class
    @Autowired
    private SportEventService sportEventService;
    @Autowired
    SportEventRepository sportEventRepository;

    //Get Method and href

    /**
     * getSportEvent method return HTTP response
     *
     * @param id sportEventId path variable
     * @return HTTP response contain headers, body and status code of SportEvent object
     */
    @GetMapping(value = "/{sportEventId}")
    public ResponseEntity<SportEvent> getSportEvent(@PathVariable("sportEventId") String id) {
            SportEvent sportEvent = sportEventService.getSportEvent(id);
            return Optional.ofNullable(sportEvent)
                            .map(user -> ResponseEntity.ok().body(user
                                                    .add(linkTo(methodOn(SportEventController.class)
                                                    .getSportEvent(id))
                                                    .withSelfRel(),
                                                    linkTo(methodOn(SportEventController.class)
                                                    .createSportEvent(sportEvent))
                                                    .withRel("createSportEvent"),
                                                    linkTo(methodOn(SportEventController.class)
                                                    .updateSportEvent(sportEvent))
                                                    .withRel("updateSportEvent"))))
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Post method
    /**
     * createSportEvent method pass request body sportEvent and return HTTP response
     * @param sportEvent from request body
     * @return HTTP response with status codes, headers and body of sportEvent
     */
    @PostMapping()
    public ResponseEntity<SportEvent> createSportEvent(
            @RequestBody SportEvent sportEvent) {
        return ResponseEntity.ok(sportEventService.createSportEvent(sportEvent));
    }
    //Put Method
    @PutMapping
    /**
     * updateSportEvent method pass request body, update sportEvent and return HTTP response
     * @param sportEvent from request body
     * @return HTTP response with status codes, headers and body of sportEvent
     */
    public ResponseEntity<SportEvent>updateSportEvent(
            @RequestBody SportEvent sportEvent){
        return ResponseEntity.ok(sportEventService.updateSportEvent(sportEvent));
    }
}


