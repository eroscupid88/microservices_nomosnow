package ca.nomosnow.eventrequest.controller;



import ca.nomosnow.eventrequest.utils.UserContext;
import ca.nomosnow.eventrequest.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * SportEventController map API and return response json
 */
@RestController
@RequestMapping(value = {"/v1/sportOrganization/{sportOrganizationId}/sportEvent"})
public class SportEventController {
    //    Log any event with Controller
    private static final Logger logger = LoggerFactory.getLogger(SportEventController.class);

}


