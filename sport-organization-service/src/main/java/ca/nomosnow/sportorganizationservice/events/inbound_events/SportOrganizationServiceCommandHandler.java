package ca.nomosnow.sportorganizationservice.events.inbound_events;

import ca.nomosnow.sportorganizationservice.events.outbound_events.SportOrganizationServiceEventConsumer;
import ca.nomosnow.sportorganizationservice.service.SportOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class accept command from sport-organization-service command channel and invoke action in SportOrganizationService Class
 */
public class SportOrganizationServiceCommandHandler {
    private final Logger logger = LoggerFactory.getLogger(SportOrganizationServiceEventConsumer.class);

    @Autowired
    SportOrganizationService sportOrganizationService;

}
