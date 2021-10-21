package ca.nomosnow.sportorganizationeventcreatedepartmentservice.controller;

import ca.nomosnow.sportorganizationeventcreatedepartmentservice.model.SportOrganizationDraft;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.service.SportOrganizationEventCreateDepartmentService;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.utils.UserContext;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.utils.UserContextHolder;
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
    private SportOrganizationEventCreateDepartmentService sportOrganizationEventCreateDepartmentService;

//    @RolesAllowed({"ADMIN","USER"})
    @RequestMapping(value = "/{sportOrganizationId}", method = RequestMethod.GET)
    private ResponseEntity<SportOrganizationDraft> getSportOrganization(@PathVariable String sportOrganizationId) {
        logger.debug("Sport Controller Correlation id: {}", UserContext.getCorrelationId());

        return ResponseEntity.ok(sportOrganizationEventCreateDepartmentService.getSportOrganization(sportOrganizationId));
    }
//    @RolesAllowed({"ADMIN","USER"})
    @PutMapping()
    private ResponseEntity<SportOrganizationDraft> updateSportOrganization(
            @RequestBody SportOrganizationDraft sportOrganizationDraft) {
        UserContextHolder.getContext();
        logger.debug("Sport Controller Correlation id: {}", UserContext.getCorrelationId());

        return ResponseEntity.ok(sportOrganizationEventCreateDepartmentService.updateSportOrganization(sportOrganizationDraft));
    }
//    @RolesAllowed({"ADMIN","USER"})
    @PostMapping()
    private ResponseEntity<SportOrganizationDraft> createSportOrganization(
            @RequestBody SportOrganizationDraft sportOrganizationDraft) {

        UserContextHolder.getContext();
        logger.debug("Sport Controller Correlation id: {}", UserContext.getCorrelationId());
        return ResponseEntity.ok(sportOrganizationEventCreateDepartmentService.createSportOrganization(sportOrganizationDraft));
    }
}
