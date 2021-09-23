package ca.nomosnow.sportorganizationservice.controller;

import ca.nomosnow.sportorganizationservice.model.SportOrganization;
import ca.nomosnow.sportorganizationservice.repository.SportOrganizationRepository;
import ca.nomosnow.sportorganizationservice.service.SportOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/sportOrganization")
public class Controller {
    static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private SportOrganizationService sportOrganizationService;

    @RequestMapping(value = "/{sportOrganizationId}", method = RequestMethod.GET)
    private ResponseEntity<SportOrganization> getSportOrganization(@PathVariable String sportOrganizationId) {
        return ResponseEntity.ok(sportOrganizationService.getSportOrganization(sportOrganizationId));
    }
    @PutMapping()
    private ResponseEntity<SportOrganization> updateSportOrganization(
            @RequestBody SportOrganization sportOrganization) {
        return ResponseEntity.ok(sportOrganizationService.updateSportOrganization(sportOrganization));
    }

    @PostMapping()
    private ResponseEntity<SportOrganization> createSportOrganization(
            @RequestBody SportOrganization sportOrganization) {
        return ResponseEntity.ok(sportOrganizationService.createSportOrganization(sportOrganization));
    }
}
