package ca.nomosnow.sportorganizationservice.controller;

import ca.nomosnow.sportorganizationservice.model.SportOrganization;
import ca.nomosnow.sportorganizationservice.repository.SportOrganizationRepository;
import ca.nomosnow.sportorganizationservice.service.SportOrganizationService;
import ca.nomosnow.sportorganizationservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping(value = "/v1/sportOrganization")
public class Controller {
    static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private SportOrganizationService sportOrganizationService;

//    @RolesAllowed({"ADMIN","USER"})
    @RequestMapping(value = "/{sportOrganizationId}", method = RequestMethod.GET)
    private ResponseEntity<SportOrganization> getSportOrganization(@PathVariable String sportOrganizationId) {
        logger.debug("Sport Controller Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        return ResponseEntity.ok(sportOrganizationService.getSportOrganization(sportOrganizationId));
    }
//    @RolesAllowed({"ADMIN","USER"})
    @PutMapping()
    private ResponseEntity<SportOrganization> updateSportOrganization(
            @RequestBody SportOrganization sportOrganization) {
        logger.debug("Sport Controller Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        return ResponseEntity.ok(sportOrganizationService.updateSportOrganization(sportOrganization));
    }
//    @RolesAllowed({"ADMIN","USER"})
    @PostMapping()
    private ResponseEntity<SportOrganization> createSportOrganization(
            @RequestBody SportOrganization sportOrganization) {

        logger.debug("Sport Controller Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return ResponseEntity.ok(sportOrganizationService.createSportOrganization(sportOrganization));
    }
}
