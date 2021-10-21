package ca.nomosnow.sportorganizationeventcreatedepartmentservice.service;

import brave.ScopedSpan;
import brave.Tracer;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.model.SportEvent;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.repository.SportEventRepository;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.repository.SportOrganizationRepository;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.ConfigService;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.model.SportOrganizationDraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SportOrganizationEventCreateDepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(SportOrganizationEventCreateDepartmentService.class);

    @Autowired
    ConfigService configService;
    @Autowired
    SportOrganizationRepository sportOrganizationRepository;
    @Autowired
    SportEventRepository sportEventRepository;
    @Autowired
    Tracer tracer;
    /**
     * findById method to find SportOrganization Object
     * @param sportOrganizationId String id
     * @return one or null if not found
     */
    public SportOrganizationDraft getSportOrganization(String sportOrganizationId) {
        Optional<SportOrganizationDraft> opt;
        ScopedSpan newSpan = tracer.startScopedSpan("getOrgDBCall");
        try {
            opt = sportOrganizationRepository.findById(sportOrganizationId);
            if (opt.isEmpty()) {
                String message = String.format("Unable to find an organization with the Organization id %s", sportOrganizationId);
                logger.error(message);
                throw new IllegalArgumentException(message);
            }logger.debug("Retrieving Organization Info: " + opt.get().toString());
            }finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.annotate("Client received");
            newSpan.finish();
        }
        return opt.get();
    }

    /**
     * createSportOrganization method to generate random Id and save it into repository
     * @param sportOrganizationDraft SportOrganization object
     * @return SportOrganization
     */
    public SportOrganizationDraft createSportOrganization(SportOrganizationDraft sportOrganizationDraft) {
        sportOrganizationDraft.setId(UUID.randomUUID().toString());
        sportOrganizationRepository.save(sportOrganizationDraft);
        return sportOrganizationDraft.withComment(configService.getProperty());
    }

    /**
     * Update sportOrganization method
     * @param sportOrganizationDraft SportOrganization
     */
    public SportOrganizationDraft updateSportOrganization(SportOrganizationDraft sportOrganizationDraft) {
        sportOrganizationRepository.save(sportOrganizationDraft);
        return sportOrganizationDraft.withComment(configService.getProperty());
    }

    /**
     * delete SportOrganization method
     * @param sportOrganizationDraft
     */
    public void deleteSportOrganization(SportOrganizationDraft sportOrganizationDraft) {
        sportOrganizationRepository.deleteById(sportOrganizationDraft.getId());

    }

    //testing
    public void createSportEvent( SportEvent sportEvent){
        sportEvent.getId();

    }
}
